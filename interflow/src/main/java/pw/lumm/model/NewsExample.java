package pw.lumm.model;

import java.util.List;

public class NewsExample {
    List<News> news;
    List<Forum> forums;
    List<Notic> notics;

    public List<Notic> getNotics() {
        return notics;
    }

    public void setNotics(List<Notic> notics) {
        this.notics = notics;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }
}
