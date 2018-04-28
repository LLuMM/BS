package pw.lumm.model;

import java.util.List;

public class AnswerExample {
    private Answer answer;
    private List<User> users;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
