package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.lumm.dao.QuestionMapper;
import pw.lumm.model.*;
import pw.lumm.service.inf.*;
import pw.lumm.utils.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ForumService forumService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    AnsewerServer ansewerServer;
    @Autowired
    SearchService searchService;

    String MSG_BAN= "您发布的文章被版主禁用，请联系版主！";
    String MSG_ALLOW = "版主已同意！";

    String MSG_FORUMBAN= "您申请的版块被禁用，请联系管理员！";
    String MSG_FORUMALLOW = "管理员已同意！";



    @Override
    public List<Question> getQuestionByForunmId(String id)  throws Exception {
        List<Question> questions = questionMapper.getQuestionByForunmId(id);
        return questions;
    }

    @Override
    public void addForum(String loginid, int type, String ftitle, String fcontent)  throws Exception {
        Forum forum = new Forum();
        Msg msg = new Msg();
        String id = UUID.randomUUID().toString();
        forum.setFid(id);
        forum.setUid(loginid);
        forum.setContent(fcontent);
        forum.setTitle(ftitle);
        forum.setType(type);
        forum.setStatus(0);
        String date = DateUtil.format(new Date());
        forum.setTime(date);

        User user = userService.getUserById(loginid);
        msg.setId(UUID.randomUUID().toString());
        msg.setFromname("申请人:" + user.getUsername());
        msg.setFid(id);
        msg.setFromid(loginid);
        msg.setTitle("申请版块：" + ftitle);
        msg.setContent(fcontent);
        msg.setToname("admin");
        msg.setToid("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7");
        msg.setTime(date);
        messageService.addMessage(msg);
        forumService.addForum(forum);
    }

    @Override
    public void setForumStatus(String id, int type)  throws Exception {

        Msg msg = new Msg();
        //设置管理员信息已读
        msg.setFromid(id);
        messageService.setMessasgeStatus(msg);

        //给版主用户回信息
        Forum forum = forumService.getFourmById(id);
        Msg msg1 = new Msg();
        String mid = UUID.randomUUID().toString();
        msg1.setId(mid);
        if (type == 2){
            msg1.setContent(MSG_FORUMBAN);
        }else if(type == 1){
            msg1.setContent(MSG_FORUMALLOW);
            userService.setUserPrivilege(forum.getUid(), type);

        }
        msg1.setFromid("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7");
        msg1.setTime(DateUtil.format(new Date()));
        msg1.setFromname("管理员");
        msg1.setToid(forum.getUid());
        msg1.setType(2);
        messageService.addMessage(msg1);
        forumService.setForumStatus(id, type);


    }

    @Override
    public void addQuestion(String loginid, String fid, String title, String value_content) throws Exception  {
        User user = userService.getUserById(loginid);
        Question question = new Question();
        question.setId(UUID.randomUUID().toString());
        question.setUserid(loginid);
        question.setFid(fid);
        question.setFrompic(user.getFace());
        question.setUname(user.getUsername());
        question.setContent(value_content);
        question.setTitle(title);
        question.setTime(DateUtil.format(new Date()));

        searchService.importQuestion(question);
        questionMapper.addQuestion(question);
    }


    @Override
    public Question getQuestionById(String id) throws Exception  {
        Question question = questionMapper.getQuestionById(id);
        int hit = question.getHits();
        ++hit;
        question.setHits(hit);
        questionMapper.setHitById(id, hit);



        return question;
    }

    @Override
    public List<Answer> getAnswerByQid(String id) throws Exception  {
        List<Answer> answers = questionMapper.getAnswerByQid(id);
        if (answers != null && answers.size() > 0)
            return answers;
        else
            return null;

    }

    @Override
    public void setTop(String id, int status) throws Exception  {
        questionMapper.setTop(id, status);
    }

    @Override
    public void setStatus(String id, int status) throws Exception  {
        Question question = questionMapper.getQuestionById(id);
        User user = userService.getUserById(question.getUserid());
        Forum forum = forumService.getFourmById(question.getFid());
        User fromUser = userService.getUserById(forum.getUid());
        Msg msg = new Msg();
        String mid = UUID.randomUUID().toString();
        msg.setId(mid);

        if (status == 2){
            msg.setContent(MSG_BAN);
        }else
            msg.setContent(MSG_ALLOW);
        msg.setTime(DateUtil.format(new Date()));
        msg.setFromid(fromUser.getUid());
        msg.setFromname(fromUser.getUsername());
        msg.setToid(user.getUid());
        msg.setToname(user.getUsername());
        msg.setType(2);
        try {
            messageService.addMessage(msg);
            questionMapper.setStatus(id, status);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Question> getQuestionByUserId(String uid) throws Exception  {
        List<Question> questions = questionMapper.getQuestionByUserId(uid);
        if (questions != null && questions.size() > 0)
            return questions;
        else
            return null;
    }

    @Override
    public void deleteByQid(String id) throws Exception  {
        questionMapper.deleteQuestionById(id);
        ansewerServer.deleteByQid(id);
        searchService.deleteQuestionById(id);
    }

    @Override
    public List<Question> getAllQuestion()  throws Exception {
        List<Question> questions = questionMapper.getAllQuestion();
        return questions;
    }

    @Override
    public void setFrompic(String uid, String url) throws Exception  {
        questionMapper.setFrompic(uid, url);
    }


}
