package pw.lumm.model;

import java.util.HashMap;
import java.util.List;

public class NewsExample {
    List<Forum> forums;
    List<Notic> notics;
    HashMap<String,List<News>> news;

    public HashMap<String, List<News>> getNews() {
        return news;
    }

    public void setNews(HashMap<String, List<News>> news) {
        this.news = news;
    }

    public List<Notic> getNotics() {
        return notics;
    }

    public void setNotics(List<Notic> notics) {
        this.notics = notics;
    }



    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }
}
