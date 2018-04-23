package pw.lumm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @RequestMapping("/add")
    public String toAdd(){

        return "question/add";
    }
    @RequestMapping("/detail")
    public String todetail(){

        return "question/detail";
    }
    @RequestMapping("/commit")
    public String add(){

        return "question/add";
    }
}
