package pw.lumm.service.inf;

import pw.lumm.model.Msg;
import pw.lumm.model.Notic;

import java.util.List;

public interface MessageService {
    List<Msg> getMessageById(String id);
    void addMessage(Msg msg);
    void addNotic(String notic);
    List<Notic> getNotics();
}
