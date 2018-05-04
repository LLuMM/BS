package pw.lumm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/test")
public class TestController {

    @RequestMapping("/response-body-test")
    @ResponseBody
    public ModelMap responseBodyTest(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("userName", "jzx");
        System.out.println(modelMap.get("userName"));
        return modelMap;
    }
}
