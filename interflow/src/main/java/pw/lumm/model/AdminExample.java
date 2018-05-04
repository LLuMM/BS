package pw.lumm.model;

import java.util.List;

public class AdminExample {
    List<User> users;
    List<Msg> msgs;
    List<Notic> notics;

    public List<Notic> getNotics() {
        return notics;
    }

    public void setNotics(List<Notic> notics) {
        this.notics = notics;
    }

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
