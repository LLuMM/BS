package pw.lumm.dao;

import pw.lumm.model.Answer;
import pw.lumm.model.News;
import pw.lumm.model.NsNews;

import java.util.List;

public interface NewsMapper {
    List<News> getHotNews(int type, int limi);
    NsNews getDetil(String n_id);

    List<News> getAllNews();

    News getNewsById(String n_id);

    void updateRead(String nid,int read);

    List<News> getNews(int type, int limi);
}
