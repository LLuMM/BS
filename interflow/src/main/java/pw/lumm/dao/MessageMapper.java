package pw.lumm.dao;

import pw.lumm.model.Msg;
import pw.lumm.model.Notic;

import java.util.List;

public interface MessageMapper {
    Msg getMessageById(String id);
    void addMessage(Msg msg);
    void addNotic(Notic notic);
    List<Notic> getNotices();
    void deleteNotice(String id);
    void setMessageStauts(Msg msg);
    void deleteByStatus(String id);
    void deleteMsgById(String id);

    String getMessageByfid(String id);

    List<Msg> getMessageByUid(String id);

    List<Msg> getMessageByUidWithStatus(String uid);
}
