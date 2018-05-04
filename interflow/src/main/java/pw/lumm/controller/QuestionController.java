package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pw.lumm.model.*;
import pw.lumm.service.inf.ForumService;
import pw.lumm.service.inf.QuestionService;
import pw.lumm.utils.FastDFSClient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {
    private String IMAGE_SERVER_URL="http://192.168.202.200/";
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ForumService forumService;
    @RequestMapping("/index")
    public String toIndex(@RequestParam String fid, Model model) {
        List<Question> questions = questionService.getQuestionByForunmId(fid);
        Forum forum = new Forum();
        forum.setFid(fid);
        ForumExample forumExample = new ForumExample();
        forumExample.setForum(forum);
        forumExample.setQuestions(questions);
        model.addAttribute("forumExample", forumExample);
        return "question/index";
    }


    @RequestMapping("/add")
    public String toAdd(String id, Model model) {
        Forum forum = forumService.getFourmById(id);
        model.addAttribute("forum", forum);
        return "question/add";
    }



    @RequestMapping("/addForum")
    public void addForum(HttpServletResponse out, String loginid, int type, String ftitle, String fcontent) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.addForum(loginid, type, ftitle, fcontent);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping("/addQuestion")
    public void addQuestion(HttpServletResponse out,  String loginid, String fid, String title, String value_content) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.addQuestion(loginid, fid, title, value_content);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));
    }


    @RequestMapping("/detail")
    public String toDetail(@RequestParam String id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = questionService.getAnswerByQid(id);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setAnswers(answers);
        questionExample.setQuestion(question);
        model.addAttribute("questionExample",questionExample);
        return "question/detail";
    }


    @RequestMapping("/setForumStatus")
    public void setForumStatus(HttpServletResponse out, int status, String id) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setForumStatus(id,status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }
    @RequestMapping("/setTop")
    public void setTop(HttpServletResponse out, String id,int status) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setTop(id,status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }

    @RequestMapping("/setStatus")
    public void setStatus(HttpServletResponse out, String id,int status) throws IOException {
        System.out.println(status);

        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setStatus(id,status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }

    @RequestMapping("/delete")
    public void deleteByQid(HttpServletResponse out,String id) throws IOException {

        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.deleteByQid(id);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value = "/upimg",method=RequestMethod.POST)
    public void imgUpload(@RequestParam MultipartFile uploadFile,HttpServletResponse out){
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String url = IMAGE_SERVER_URL + path;
            System.out.println(url);
            out.getWriter().write(gson.toJson(url));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}
