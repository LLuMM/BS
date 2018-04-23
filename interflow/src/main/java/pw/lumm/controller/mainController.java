package pw.lumm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LM on 2018/4/19.
 */
@Controller

public class mainController {
    @RequestMapping(value = "/index")
    public String toIndex(){
        return "index";
    }
}
