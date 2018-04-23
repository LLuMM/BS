package pw.lumm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pw.lumm.model.User;
import pw.lumm.service.inf.UserService;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LM on 2018/4/12.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @RequestMapping(value = "/toregister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user) {

        userService.register(user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletResponse out, String username, String password, Model model) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            User us = userService.login(username, password);
            if (null!=us) {
                model.addAttribute("user",us);
                response.Status = true;
            }
        }catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();

        }

        out.getWriter().write(gson.toJson(response));


    }


    @RequestMapping(value = "/loginout")
    public String loginout(HttpServletRequest request, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }

    @RequestMapping(value = "/userinfo")
    public String toUserInfo(String uid) {

        return "user/index";
    }
    @RequestMapping(value = "/home")
    public String toUserhome(String uid) {
        return "user/home";
    }

    @RequestMapping(value = "/check")
    public void checkUser(HttpServletRequest request, HttpServletResponse out) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        if(request.getSession().getAttribute("user")==null) {
            response.Status = false;
            response.Message = "请先登录！";
            out.getWriter().write(gson.toJson(response));
        }
    }



}
