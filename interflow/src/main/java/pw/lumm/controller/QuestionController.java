package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.model.*;
import pw.lumm.service.inf.QuestionService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;


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
        Forum forum = questionService.getForumById(id);

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
    public void addQuestion(HttpServletResponse out, String uname, String loginid, String fid, String title, String value_content) throws IOException {
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

}
