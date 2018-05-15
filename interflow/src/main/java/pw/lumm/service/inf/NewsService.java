package pw.lumm.service.inf;

import pw.lumm.model.News;
import pw.lumm.model.NsNews;

import java.util.List;

public interface NewsService {

     /*获取热门信息
     * */
     List<News> getHotNews(int type, int limi) throws Exception ;

     /*获取新闻具体信息
     * */
     NsNews getNewsDetail(String n_id) throws Exception ;


    List<News> getAllNews() throws Exception ;

    News getNewsById(String n_id) throws Exception ;

    void updateRead(String nid,int read);
}
