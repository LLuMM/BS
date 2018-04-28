package pw.lumm.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import pw.lumm.model.AdminExample;
import pw.lumm.model.Msg;
import pw.lumm.model.User;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.UserService;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by LM on 2018/4/12.
 */
@Controller
@RequestMapping("/user")

public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/toregister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getSex());
        userService.register(user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request,HttpServletResponse out, String username, String password, Model model) throws IOException {
        HttpSession session = request.getSession();
        out.setContentType("text/html; charset=utf-8");
        try {
            User us = userService.login(username, password);
            if (null != us) {

                session.setAttribute("user", us);
                response.Status = true;
                if ("admin".equals(username)) {
                   response.user = 1;
                }
                else response.user = 0;

            }
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
        }
        out.getWriter().write(gson.toJson(response));

    }
    @RequestMapping(value = "/toadmin")
    public String toadmin(Model model) {
        List<User> users = userService.getAllUserByForum();
        List<Msg> msgs = messageService.getMessageById("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7");
        AdminExample adminExample = new AdminExample();
        adminExample.setUsers(users);
        adminExample.setMsgs(msgs);

        model.addAttribute("adminExample", adminExample);
        return "user/admin";
    }

    @RequestMapping(value = "/loginout")
    public void loginout(HttpServletRequest request,HttpServletResponse out, SessionStatus sessionStatus) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user","");
        response.Status = true;
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/userindex")
    public String toUserInfo(@RequestParam String uid, Model model) {
        if("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7".equals(uid)){
            return "redirect:toadmin";
        }
        List<Msg> msgs = messageService.getMessageById(uid);

        model.addAttribute("msgs",msgs);
        return "user/index";
    }

    @RequestMapping(value = "/check")
    public void checkUser(HttpServletRequest request, HttpServletResponse out) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        if (request.getSession().getAttribute("user") == null) {
            response.Status = false;
            response.Message = "请先登录！";
            out.getWriter().write(gson.toJson(response));
        }
    }

    @RequestMapping(value = "/applyForum")
    public String applyForum() {

        return "index";
    }


}
