package pw.lumm.service.impl;

import org.springframework.stereotype.Service;
import pw.lumm.dao.NewsMapper;
import pw.lumm.model.*;
import pw.lumm.service.inf.NewsService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Override
    public List<News> getHotNews(int type, int limi) throws Exception  {
        List<News> list = newsMapper.getHotNews(type, limi);
        if (list != null && list.size() > 0)
            return list;
        else
            return null;
    }

    @Override
    public NsNews getNewsDetail(String n_id)  throws Exception {
        NsNews nsNews = newsMapper.getDetil(n_id);
        if (nsNews != null)
            return nsNews;
        else
            return null;
    }

    @Override
    public List<News> getAllNews() throws Exception  {
        List<News> newsList = newsMapper.getAllNews();
        return newsList;
    }

    @Override
    public News getNewsById(String n_id) throws Exception  {
        News news = newsMapper.getNewsById(n_id);
        if (news!=null)
            return news;
        else
            return null;
    }

    @Override
    public void updateRead(String nid,int read) {
        newsMapper.updateRead(nid,read);
    }


}
