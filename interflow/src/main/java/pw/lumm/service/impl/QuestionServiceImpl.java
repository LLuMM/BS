package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.lumm.dao.ForumMapper;
import pw.lumm.dao.MessageMapper;
import pw.lumm.dao.QuestionMapper;
import pw.lumm.dao.UserMapper;
import pw.lumm.model.*;
import pw.lumm.service.inf.ForumService;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.QuestionService;
import pw.lumm.service.inf.UserService;
import sun.plugin2.message.Message;

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

    @Override
    public List<Forum> getForum(int type, int status) {
        List<Forum> forumList = questionMapper.getForum(type, status);
        return forumList;
    }

    @Override
    public Forum getForumById(String id) {
         Forum forum= forumService.getFourmById(id);
        return forum;

    }
    @Override
    public List<Question> getQuestionByForunmId(String id) {
        List<Question> questions = questionMapper.getQuestionByForunmId(id);
        return questions;

    }
    @Override
    public void addForum(String loginid, int type, String ftitle, String fcontent) {
        Forum forum =new Forum();
        Msg msg = new Msg();
        String id = UUID.randomUUID().toString();
        forum.setFid(id);
        forum.setUid(loginid);
        forum.setContent(fcontent);
        forum.setTitle(ftitle);
        forum.setType(type);
        forum.setStatus(0);
        forum.setTime(new Date());

        User user = userService.getUserById(loginid);
        msg.setId(UUID.randomUUID().toString());
        msg.setFromname("申请人:"+user.getUsername());
        msg.setFid(id);
        msg.setFromid(loginid);
        msg.setContent("申请版块："+ ftitle);
        msg.setToid("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7");
        msg.setTime(new Date());
        messageService.addMessage(msg);
        forumService.addForum(forum);
    }

    @RequestMapping("/setForumStatus")
    public void setForumStatus(String id,int type){
        forumService.setForumStatus(id,type);

        Forum forum = forumService.getFourmById(id);
        userService.setUserPrivilege(forum.getUid(),type);

    }

    @Override
    public void addQuestion(String loginid,  String fid, String title, String value_content) {
        User user = userService.getUserById(loginid);
        System.out.println(user.getUsername());
        Question question =new Question();
        question.setId(UUID.randomUUID().toString());
        question.setUserid(loginid);
        question.setFid(fid);
        question.setUname(user.getUsername());
        question.setContent(value_content);
        question.setTitle(title);
        question.setTime(new Date());
        questionMapper.addQuestion(question);
    }

    @Override
    public Question getQuestionById(String id) {
        Question question = questionMapper.getQuestionById(id);
        int hit = question.getHits();
        ++hit;
        question.setHits(hit);
        questionMapper.setHitById(id,hit);

        return question;
    }

    @Override
    public List<Answer> getAnswerByQid(String id) {
        List<Answer> answers = questionMapper.getAnswerByQid(id);
        return answers;
    }
}
