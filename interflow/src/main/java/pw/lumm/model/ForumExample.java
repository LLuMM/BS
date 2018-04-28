package pw.lumm.model;

import java.util.List;

public class ForumExample {
    private Forum forum;
    private List<Question> questions;

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
