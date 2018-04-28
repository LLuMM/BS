package pw.lumm.model;

import java.util.List;

public class NsNewsExample{

    private NsNews nsNews;
    private List<Answer> answers;

    public NsNews getNsNews() {
        return nsNews;
    }

    public void setNsNews(NsNews nsNews) {
        this.nsNews = nsNews;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
