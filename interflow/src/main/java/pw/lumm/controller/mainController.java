package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.lumm.model.News;
import pw.lumm.model.NewsExample;
import pw.lumm.model.Notic;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.NewsService;

import java.util.List;

/**
 * Created by LM on 2018/4/19.
 */
@Controller

public class mainController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/index")
    public String toIndex(Model model){
        List<News> newsList = newsService.getHotNews(1,4);
        List<Notic> notics = messageService.getNotics();
        NewsExample indexinfo = new NewsExample();
        indexinfo.setNotics(notics);
        indexinfo.setNews(newsList);
        model.addAttribute("indexinfo", indexinfo);
        return "index";
    }

}
