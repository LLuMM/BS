package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pw.lumm.dao.MessageMapper;
import pw.lumm.model.Msg;
import pw.lumm.model.Notic;
import pw.lumm.service.inf.MessageService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<Msg> getMessageById(String id) {
        List<Msg> msgs = messageMapper.getMessageById(id);
        return msgs;
    }

    @Override
    public void addMessage(Msg msg) {
        messageMapper.addMessage(msg);
    }

    @Override
    public void addNotic(String notic) {
        Notic notic1 = new Notic();
        notic1.setContent(notic);
        notic1.setId(UUID.randomUUID().toString());
        notic1.setTime(new Date());
        messageMapper.addNotic(notic1);
    }

    @Override
    public List<Notic> getNotics() {
        List<Notic> notics = messageMapper.getNotices();
        return notics;
    }

}
