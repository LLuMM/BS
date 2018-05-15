package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pw.lumm.dao.AnsewerMapper;
import pw.lumm.model.*;
import pw.lumm.service.inf.*;
import pw.lumm.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AnswerServerImpl implements AnsewerServer {
    @Autowired
    AnsewerMapper ansewerMapper;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    QuestionService questionService;

    @Autowired
    NewsService newsService;

    @Override
    public void addAnswer(String uid, String id, String content, String username) throws Exception {

        String aid = UUID.randomUUID().toString();
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH mm");


        User user = userService.getUserById(uid);
        Question question = questionService.getQuestionById(id);

        String name = user.getUsername();
        Answer answer = new Answer();
        answer.setToid(question.getUserid());
        answer.setAnswerTo(question.getUname());
        answer.setUid(uid);
        answer.setContent(content);

        answer.setTime(DateUtil.format(date));
        answer.setQid(id);
        answer.setAid(aid);
        answer.setUsername(name);
        answer.setQuestionTitle(question.getTitle());


        //回复后对相应的用户设置消息
        String mid = UUID.randomUUID().toString();
        Msg msg = new Msg();
        msg.setId(mid);
        msg.setFromname(name);
        msg.setContent(content);
        msg.setTime(DateUtil.format(date));
        msg.setFromid(uid);
        msg.setToname(question.getUname());
        msg.setToid(question.getUserid());
        try {
            ansewerMapper.addAnswer(answer);
            messageService.addMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*通过type判断是在系统文章对用户回复 还是在用户自己文章中对用户的回复（在answer中存入文章标题）*/
    @Override
    public void addAnswerToUser(String qid, String fromid, String toid, String content, int type) throws Exception {
        String aid = UUID.randomUUID().toString();
        User fromUser = userService.getUserById(fromid);
        User toUser = userService.getUserById(toid);
        String fromname = fromUser.getUsername();
        String toUserName = toUser.getUsername();
        Date date = new Date();
        Msg msg = new Msg();

        Answer answer = new Answer();
        if (type == 0) {
            NsNews nsNews = newsService.getNewsDetail(qid);
            answer.setQuestionTitle(nsNews.getTitle());
            msg.setTitle(nsNews.getTitle());
            msg.setFid(nsNews.getNsid());
        } else if (type == 1) {
            Question question = questionService.getQuestionById(qid);
            answer.setQuestionTitle(question.getTitle());
            msg.setTitle(question.getTitle());
            msg.setFid(question.getId());
        }
        answer.setUid(fromid);
        answer.setAid(aid);
        answer.setQid(qid);
        answer.setToid(toUser.getUid());
        answer.setUsername(fromname);
        answer.setTime(DateUtil.format(date));
        answer.setAnswerTo(toUser.getUsername());
        answer.setContent(content);

        //回复后对相应的用户设置消息
        String mid = UUID.randomUUID().toString();

        msg.setId(mid);
        msg.setFromname(fromname);
        msg.setContent(content);
        msg.setToname(toUserName);
        msg.setTime(DateUtil.format(date));
        msg.setFromid(fromUser.getUid());
        msg.setToid(toUser.getUid());

        ansewerMapper.addAnswer(answer);
        messageService.addMessage(msg);


    }

    @Override
    public List<Answer> getAnswerByUserId(String uid) throws Exception {
        List<Answer> answers = ansewerMapper.getAnswerByUserId(uid);

        if (answers != null && answers.size() > 0)
            return answers;
        else
            return null;

    }

    @Override
    public void addAnswerToNews(String uid, String nsid, String content, String username) throws Exception {
        String aid = UUID.randomUUID().toString();

        Date date = new Date();
        User user = userService.getUserById(uid);
        String name = user.getUsername();
        Answer answer = new Answer();
        NsNews nsNews = newsService.getNewsDetail(nsid);
        answer.setUid(uid);
        answer.setContent(content);
        answer.setTime(DateUtil.format(date));
        answer.setQid(nsid);
        answer.setAid(aid);
        answer.setUsername(name);

        answer.setQuestionTitle(nsNews.getTitle());
        ansewerMapper.addAnswer(answer);


    }

    @Override
    public List<Answer> getAnswerByNewsId(String n_id) throws Exception {
        List<Answer> answers = ansewerMapper.getAnswerByNewId(n_id);
        if (answers != null && answers.size() > 0)
            return answers;
        else
            return null;
    }

    @Override
    public void deleteByQid(String id) throws Exception {
        ansewerMapper.deleteByQid(id);
    }
}
