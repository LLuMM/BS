package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.model.SearchItemExample;
import pw.lumm.service.inf.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/keyword")
    public String getSearch(@RequestParam String keyword, Model model){
        try {
            SearchItemExample searchItemExample = searchService.search(keyword);
            if(searchItemExample.getSearchItems()!=null&&searchItemExample.getSearchItems().size()>0){
                model.addAttribute("searchItemExample", searchItemExample);
            }
            else{
                model.addAttribute("searchItemExample", null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "search/index";
    }

    @RequestMapping("/importTitle")
    public void importTitle(){
        try {
            searchService.importAllTitle();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("/deleteAllNews")
    public void deleteAllNews(){
        try {
            searchService.deleteAllNews();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
