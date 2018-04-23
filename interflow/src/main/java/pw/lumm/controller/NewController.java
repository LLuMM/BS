package pw.lumm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/new")
public class NewController {


    @RequestMapping("/index")
    public String toIndex(){
        return "new/index";
    }
}
