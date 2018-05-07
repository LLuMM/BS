package pw.lumm.service.inf;

import pw.lumm.model.News;
import pw.lumm.model.NsNews;

import java.util.List;

public interface NewsService {

     /*获取热门信息
     * */
     List<News> getHotNews(int type, int limi);

     /*获取新闻具体信息
     * */
     NsNews getNewsDetail(String n_id);


    List<News> getAllNews();

    News getNewsById(String n_id);
}
