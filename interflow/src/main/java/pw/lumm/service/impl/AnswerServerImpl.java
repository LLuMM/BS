package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pw.lumm.dao.AnsewerMapper;
import pw.lumm.model.Answer;
import pw.lumm.model.Msg;
import pw.lumm.model.User;
import pw.lumm.service.inf.AnsewerServer;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.UserService;

import java.util.Date;
import java.util.UUID;

public class AnswerServerImpl implements AnsewerServer {
    @Autowired
    AnsewerMapper ansewerMapper;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @Override
    public void addAnswer(String uid, String id, String content, String username) {
        String aid = UUID.randomUUID().toString();
        Date date = new Date();
        User user = userService.getUserById(uid);
        String name = user.getUsername();
        Answer answer = new Answer();
        answer.setUid(uid);
        answer.setContent(content);
        answer.setTime(date);
        answer.setQid(id);
        answer.setAid(aid);
        answer.setUsername(name);
        ansewerMapper.addAnswer(answer);

        //回复后对相应的用户设置消息
        String mid = UUID.randomUUID().toString();
        Msg msg = new Msg();
        msg.setId(mid);
        msg.setFromname(name);
        msg.setContent(content);
        msg.setTime(date);
        msg.setFromid(uid);
        messageService.addMessage(msg);

    }

    @Override
    public void addAnswerToUser(String qid,String fromid, String toid, String content) {
        String aid = UUID.randomUUID().toString();
        User fromUser = userService.getUserById(fromid);
        User toUser = userService.getUserById(toid);
        String fromname = fromUser.getUsername();
        String toUserName = toUser.getUsername();
        Date date = new Date();


        Answer answer = new Answer();
        answer.setUid(fromid);
        answer.setAid(aid);
        answer.setQid(qid);
        answer.setUsername(fromname);
        answer.setTime(date);
        answer.setAnswerTo(toUser.getUsername());
        answer.setContent(content);
        ansewerMapper.addAnswerToUser(answer);
        //回复后对相应的用户设置消息
        String mid = UUID.randomUUID().toString();
        Msg msg = new Msg();
        msg.setId(mid);
        msg.setFromname(fromname);
        msg.setContent(content);
        msg.setToname(toUserName);
        msg.setTime(date);
        msg.setFromid(fromUser.getUid());
        msg.setToid(toUser.getUid());
        messageService.addMessage(msg);


    }
}
