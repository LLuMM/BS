package pw.lumm.model;

import java.util.List;
import java.util.Map;

public class AdminExample {
    List<User> users;
    List<Msg> msgs;
    List<User> forumUser;

    public List<User> getForumUser() {
        return forumUser;
    }

    public void setForumUser(List<User> forumUser) {
        this.forumUser = forumUser;
    }

    List<Notic> notics;
   Map<String,List<Forum>> userForum;

    public Map<String, List<Forum>> getUserForum() {
        return userForum;
    }

    public void setUserForum(Map<String, List<Forum>> userForum) {
        this.userForum = userForum;
    }

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
