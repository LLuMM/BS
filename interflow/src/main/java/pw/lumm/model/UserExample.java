package pw.lumm.model;

import java.util.HashMap;
import java.util.List;

public class UserExample {
    private User user;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Msg> msgs;
    List<Forum> forums;

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    HashMap<String,List<Question>> forumListMap;

    public HashMap<String, List<Question>> getForumListMap() {
        return forumListMap;
    }

    public void setForumListMap(HashMap<String, List<Question>> forumListMap) {
        this.forumListMap = forumListMap;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
