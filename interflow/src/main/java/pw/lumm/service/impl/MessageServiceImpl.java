package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pw.lumm.dao.MessageMapper;
import pw.lumm.model.Msg;
import pw.lumm.model.Notic;
import pw.lumm.model.User;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.UserService;
import pw.lumm.utils.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    UserService userService;

    @Override
    public List<Msg> getMessageByUid(String id) throws Exception{
        List<Msg> msgs = messageMapper.getMessageByUid(id);
        return msgs;
    }

    @Override
    public Msg getMessageById(String id) {
        Msg msg = messageMapper.getMessageById(id);
        return msg;
    }

    @Override
    public void addMessage(Msg msg) throws Exception {
        messageMapper.addMessage(msg);
    }

    @Override
    public void addNotic(String notic) throws Exception {
        Notic notic1 = new Notic();
        notic1.setContent(notic);
        notic1.setId(UUID.randomUUID().toString());
        notic1.setTime(DateUtil.format(new Date()));
        messageMapper.addNotic(notic1);
    }

    @Override
    public List<Notic> getNotics() throws Exception {
        List<Notic> notics = messageMapper.getNotices();
        if (notics != null && notics.size() > 0)
            return notics;
        else
            return null;
    }

    @Override
    public void deleteNotice(String id) throws Exception  {
        messageMapper.deleteNotice(id);
    }

    @Override
    public void setMessasgeStatus(Msg msg) throws Exception {
        messageMapper.setMessageStauts(msg);
    }

    @Override
    public void deleteByStatus(String id) throws Exception {
        messageMapper.deleteByStatus(id);
    }

    @Override
    public void deleteMsgById(String id) throws Exception {
        messageMapper.deleteMsgById(id);
    }

    @Override
    public void chatMessage(String fromid, String toid) throws Exception {
        User fromuser = userService.getUserById(fromid);
        User touser = userService.getUserById(toid);
        Msg msg = new Msg();
        msg.setId(UUID.randomUUID().toString());
        msg.setFromid(fromuser.getUid());
        msg.setFromname(fromuser.getUsername());
        msg.setType(2);
        msg.setToid(touser.getUid());
        msg.setToname(touser.getUsername());
        msg.setContent(fromuser.getUsername() + "邀请你聊天");
        msg.setTime(DateUtil.format(new Date()));
        System.out.println(msg.toString());
        try {
            messageMapper.addMessage(msg);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public String getMessageByfid(String fid) {
       String id= messageMapper.getMessageByfid(fid);
        return id;
    }

    @Override
    public boolean getMessageByUidWithStatus(String uid) {
       List<Msg> msgs= messageMapper.getMessageByUidWithStatus(uid);
       if (msgs!=null&&msgs.size()>0)
       {
           return true;
       }else
        return false;
    }


}
