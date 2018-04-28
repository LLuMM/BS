package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.NewsMapper;
import pw.lumm.model.*;
import pw.lumm.service.inf.NewsService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Override
    public List<News> getHotNews(int type, int limi) {
        List<News> list = newsMapper.getHotNews(type, limi);
        return list;
    }

    @Override
    public NsNewsExample getNewsDetail(String n_id) {
        NsNewsExample nsNewsExample = new NsNewsExample();
        NsNews nsNews = newsMapper.getDetil(n_id);
        nsNewsExample.setNsNews(nsNews);
        List<Answer> answers = newsMapper.getNewsAnswer(n_id);
        nsNewsExample.setAnswers(answers);
        return nsNewsExample;
    }
    @Override
    public int addAnswer(String uid, String nsid, String content ,String username) {
        String aid = UUID.randomUUID().toString();

        Answer answer = new Answer();
        answer.setUid(uid);
        answer.setContent(content);
        answer.setTime(new Date());
        answer.setQid(nsid);
        answer.setAid(aid);
        answer.setUsername(username);
        int id = newsMapper.insertAnswer(answer);
        return id;
    }


}
