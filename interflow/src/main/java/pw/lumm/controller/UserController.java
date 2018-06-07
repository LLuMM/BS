package pw.lumm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import pw.lumm.model.*;
import pw.lumm.service.inf.*;
import pw.lumm.utils.FastDFSClient;
import pw.lumm.utils.SendCode;
import pw.lumm.utils.duanxin.SendUtil;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LM on 2018/4/12.
 */
@Controller
@RequestMapping("/user")

public class UserController extends BaseController {

    private String IMAGE_SERVER_URL = "http://192.168.202.200/";

    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnsewerServer ansewerServer;

    @Autowired
    ForumService forumService;
    private String VC = "123";
    private String adminid = "2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7";
    @RequestMapping(value = "/toregister")
    public String toRegister() {
        return "register";
    }

    /*通过短信接口设置verifycod值*/
    @RequestMapping(value = "/getVerifycode")
    public void getVerifycode() {
    }

    @RequestMapping("/checkUsername")
    public void checkUsername(HttpServletResponse out, String username) throws Exception {
        Boolean flag = userService.checkUser(username);
        if (flag) {
            response.Status = true;
        } else {
            response.Status = false;
            response.Message = "用户名已存在！";
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(User user, HttpServletRequest request, HttpServletResponse out) throws Exception {
        out.setContentType("text/html; charset=utf-8");
        String code = (String) request.getSession().getAttribute("code");
        String verifycode = user.getVerifycode();
        if (code == null) {
            response.Message = "验证码失效，请重新获取！";
            response.Status = false;
        } else if ("".equals(verifycode) || !code.equals(verifycode) || verifycode == null) {
            response.Message = "验证码有错！";
            response.Status = false;

        } else {
            userService.register(user);
            response.Message = "注册成功，请重新登陆！";
            response.Status = true;
            request.getSession().setAttribute("code", "");
        }
        out.getWriter().write(gson.toJson(response));

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse out, String username, String password, Model model) throws Exception {
        HttpSession session = request.getSession();
        out.setContentType("text/html; charset=utf-8");
        try {
            User us = userService.login(username, password);
            userService.setStatus(username, 1);
            if (null != us) {
                session.setAttribute("user", us);
                response.Status = true;
                if ("admin".equals(username)) {
                    response.user = 1;
                } else
                    response.user = 0;

            }
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/toadmin")
    public String toadmin(Model model) throws Exception {
        //获取所有用户
        List<User> users = userService.getAllUser();
        //移除管理员用户
        User admin = userService.getUserById(adminid);
        users.remove(admin);
        //获取版主用户
        List<User> forumusers = userService.getAllUserByForum();
        //获取管理员信息
        List<Msg> msgs = messageService.getMessageByUid(adminid);
        //保存版主管理的版块
        Map<String, List<Forum>> userForum = new HashMap<>();
        AdminExample adminExample = new AdminExample();
        adminExample.setForumUser(forumusers);
        adminExample.setUsers(users);

        //获取相关用户的管理版块
        for (int i = 0; i < forumusers.size(); i++) {
            userForum.put(forumusers.get(i).getUsername(), forumService.getFourmByUserId(forumusers.get(i).getUid()));
        }
        adminExample.setUserForum(userForum);
        adminExample.setMsgs(msgs);
        List<Notic> noticList = messageService.getNotics();
        adminExample.setNotics(noticList);
        model.addAttribute("adminExample", adminExample);
        return "user/admin";
    }

    @RequestMapping(value = "/loginout")
    public void loginout(HttpServletRequest request, HttpServletResponse out, SessionStatus sessionStatus) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user", "");
        userService.setStatus(user.getUsername(), 0);
        response.Status = true;
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/updateUser")
    public void updateUser(HttpServletResponse out, User user) throws Exception {
        out.setContentType("text/html; charset=utf-8");
        try {
            userService.updateUser(user);
            response.Status = true;
            response.Message = "修改成功";
        } catch (Exception e) {
            response.Status = false;
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/userindex")
    public String toUserIndex(@RequestParam String uid, Model model) throws Exception {
        if ("2abe95d2-fa87-4af0-8e3b-b1ca70b9e7b7".equals(uid)) {
            return "redirect:toadmin";
        }
        List<Msg> msgs = messageService.getMessageByUid(uid);
        List<Question> selfquestions = questionService.getQuestionByUserId(uid);
        UserExample userindex = new UserExample();
        List<Forum> forums = forumService.getFourmByUserId(uid);
        userindex.setForums(forums);
        if (forums != null && forums.size() > 0) {
            HashMap<String, List<Question>> forumListMap = new HashMap<>();
            for (int i = 0; i < forums.size(); i++) {
                List<Question> questions = questionService.getQuestionByForunmId(forums.get(i).getFid());
                forumListMap.put(forums.get(i).getTitle(), questions);
            }
            userindex.setForumListMap(forumListMap);
        }

        User user = userService.getUserById(uid);
        userindex.setQuestions(selfquestions);
        userindex.setMsgs(msgs);
        userindex.setUser(user);
        model.addAttribute("userindex", userindex);
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

    @RequestMapping(value = "/userinfo")
    public String UserInfo(@RequestParam String uid, Model model) {
        User user = null;
        try {
            user = userService.getUserById(uid);

            List<Question> questions = questionService.getQuestionByUserId(uid);
            List<Answer> answers = ansewerServer.getAnswerByUserId(uid);
            UserExample userExample = new UserExample();
            userExample.setWho(1);
            userExample.setUser(user);
            userExample.setAnswers(answers);
            userExample.setQuestions(questions);
            model.addAttribute("userExample", userExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/info";
    }

    @RequestMapping(value = "/touserinfo")
    public String toUserInfo(@RequestParam String uid, Model model, HttpServletRequest request) {
        User loginUser = null;
        try {
            Object obj = request.getSession().getAttribute("user");
            if (obj != null && obj != "") {
                loginUser = (User) request.getSession().getAttribute("user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = null;
        //进行用户判断如果当前用户与所查看的用户是同一用户
        try {
            user = userService.getUserById(uid);
            if (loginUser!=null&&user.getUid().equals(loginUser.getUid())) {
                return "redirect:/user/userinfo?uid=" + user.getUid();
            }

            List<Question> questions = questionService.getQuestionByUserId(uid);
            List<Answer> answers = ansewerServer.getAnswerByUserId(uid);
            UserExample userExample = new UserExample();
            userExample.setWho(0);
            if (loginUser != null) {
                Friends friends = userService.checkfriend(uid, loginUser.getUid());
                if (friends != null) {
                    userExample.setIsfriend(true);
                } else
                    userExample.setIsfriend(false);
            }


            int read = user.getVisits();
            read = ++read;
            user.setVisits(read);
            userService.setVisits(read);
            userExample.setWho(0);

            userExample.setUser(user);
            userExample.setAnswers(answers);
            userExample.setQuestions(questions);
            model.addAttribute("userExample", userExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/info";
    }


    @RequestMapping(value = "/upimg", method = RequestMethod.POST)
    public String imgUpload(@RequestParam MultipartFile uploadFile, String uid) {
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String url = IMAGE_SERVER_URL + path;
            userService.setFace(uid, url);
            questionService.setFrompic(uid, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/index";
    }

    @RequestMapping(value = "/addfriend")
    public void addfriend(HttpServletResponse out, String userid, String friendid, String msg) throws Exception {
        out.setContentType("text/html; charset=utf-8");
        try {
            userService.addfriend(userid, friendid, msg);
            response.Status = true;
            response.Message = "发送成功";

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));
    }
/*    @RequestMapping(value = "/checkfriend")
    public void checkfriend( HttpServletResponse out, String userid, String friendid) throws Exception {
        out.setContentType("text/html; charset=utf-8");
        try{
            User user = userService.checkfriend(userid,friendid);
            if (user!=null){
                response.Status = false;
                response.Message = "已是好友关系";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));
    }*/

    @RequestMapping(value = "/friendRequest")
    public void friendRequest(HttpServletResponse out, String mid, int status) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            userService.friendRequest(mid, status);
            response.Status = true;
            response.Message = "发送成功";

        } catch (Exception e) {

        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/friendchat")
    public String friendchat(String uid, Model model) {
        List<User> users = null;
        try {
            users = userService.getFriendsById(uid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("users", users);
        return "user/friendchat";
    }

    @RequestMapping(value = "/sendcode")
    public void sendcode(HttpServletRequest request, HttpServletResponse out, String phone) throws IOException {
        try {
            SendUtil sendUtil = new SendUtil();
            String code = sendUtil.singleSend(phone);
            request.getSession().setAttribute("code", code);
            System.out.println(code);
            response.Status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));
    }

}
