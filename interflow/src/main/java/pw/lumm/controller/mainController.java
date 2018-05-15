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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String toIndex(Model model) throws Exception{
        List<News> news1= newsService.getHotNews(1,4);
        List<News> news2 = newsService.getHotNews(0,5);
        List<Notic> notics = messageService.getNotics();
        NewsExample indexinfo = new NewsExample();
        HashMap<String,List<News>> mapnews = new HashMap<>();

        mapnews.put("new1",news1);
        mapnews.put("new2",news2);
        indexinfo.setNews(mapnews);
        indexinfo.setNotics(notics);
        model.addAttribute("indexinfo", indexinfo);
        return "index";
    }

}
