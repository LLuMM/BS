package pw.lumm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pw.lumm.model.User;
import pw.lumm.service.inf.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LM on 2018/4/12.
 */
@Controller
@RequestMapping("/user")
public class UserController{


    @Autowired
    UserService userService;
    @RequestMapping(value = "/insert")
    public ModelAndView insert(){
        List<User> list = new ArrayList<User>();

        list.add(new User("12", "as", "nan"));
        list.add(new User("13", "af", "nan"));
        list.add(new User("14", "qw", "nv"));
        list.add(new User("15", "fg", "nv"));
        userService.insert(new User("12", "as", "na"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
