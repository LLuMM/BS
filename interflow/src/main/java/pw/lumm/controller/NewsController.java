package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.model.*;
import pw.lumm.service.inf.*;
import pw.lumm.utils.thrift.NewsFresh;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/new")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private AnsewerServer ansewerServer;
    @Autowired
    private ForumService forumService;


    @RequestMapping("/index")
    public String toIndex(@RequestParam int type, Model model) throws Exception {

        NewsExample newsExample = new NewsExample();
        List<Forum> forumList = forumService.getForum(type, 1);
        HashMap<String, List<News>> mapnews = new HashMap<>();

        List<News> newsLists = newsService.getHotNews(type, 10);
        newsExample.setForums(forumList);

        mapnews.put("new", newsLists);
        newsExample.setNews(mapnews);
        model.addAttribute("newsExample", newsExample);
        return "new/index";
    }

    @RequestMapping("/detail")
    public String todetail(@RequestParam String n_id, Model model) throws Exception {
        NsNewsExample nsNewsExample = new NsNewsExample();
        News news = newsService.getNewsById(n_id);

        if (news != null) {
            int read = news.getReadnum();
            read += 1;
            news.setReadnum(read);
            newsService.updateRead(n_id, read);
            List<Answer> answers = ansewerServer.getAnswerByNewsId(n_id);
            nsNewsExample.setNews(news);
            nsNewsExample.setAnswers(answers);
            model.addAttribute("nsNewsExample", nsNewsExample);
            return "new/newsdetail";
        } else {
            NsNews nsNews = newsService.getNewsDetail(n_id);
            News news1 = newsService.getNewsById(nsNews.getNid());
            int read = news1.getReadnum();
            read += 1;
            news1.setReadnum(read);
            newsService.updateRead(nsNews.getNid(), read);
            List<Answer> answers = ansewerServer.getAnswerByNewsId(n_id);
            nsNewsExample.setAnswers(answers);
            nsNewsExample.setNsNews(nsNews);
            model.addAttribute("nsNewsExample", nsNewsExample);
            return "new/detail";
        }
    }

    @RequestMapping("/addAnswer")
    public void addAnswer(HttpServletResponse out, String uid, String nsid, String content, String username) throws Exception {
        out.setContentType("text/html; charset=utf-8");
        try {
            ansewerServer.addAnswerToNews(uid, nsid, content, username);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
        }
        out.getWriter().write(gson.toJson(response));
    }


}
