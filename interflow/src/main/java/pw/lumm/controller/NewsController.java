package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.model.*;
import pw.lumm.service.inf.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/new")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnsewerServer ansewerServer;
    @Autowired
    private ForumService forumService;


    @RequestMapping("/index")
    public String toIndex(@RequestParam int type, Model model) {
        List<News> newsLists = newsService.getHotNews(type, 5);
        List<Forum> forumList = forumService.getForum(type, 1);
        NewsExample newsExample = new NewsExample();
        newsExample.setForums(forumList);
        newsExample.setNews(newsLists);
        model.addAttribute("newsExample", newsExample);
        return "new/index";
    }

    @RequestMapping("/detail")
    public String todetail(@RequestParam String n_id, Model model) {
        NsNewsExample nsNewsExample = new NsNewsExample();
        NsNews nsNews = newsService.getNewsDetail(n_id);
        List<Answer> answers = ansewerServer.getAnswerByNewsId(n_id);
        nsNewsExample.setAnswers(answers);
        nsNewsExample.setNsNews(nsNews);
        model.addAttribute("nsNewsExample", nsNewsExample);
        return "new/detail";
    }

    @RequestMapping("/addAnswer")
    public void addAnswer(HttpServletResponse out, String uid, String nsid, String content, String username) throws IOException {
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
