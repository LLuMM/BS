package pw.lumm.service.inf;

import pw.lumm.model.News;
import pw.lumm.model.NsNewsExample;
import pw.lumm.model.User;

import java.util.List;

public interface NewsService {

    public List<News> getHotNews(int type, int limi);
    public NsNewsExample getNewsDetail(String n_id);
    public int addAnswer(String uid, String nsid, String content,String username);

}
