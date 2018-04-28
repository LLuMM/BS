package pw.lumm.model;

import java.util.List;

public class AdminExample {
    List<User> users;
    List<Msg> msgs;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }
}
