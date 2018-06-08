package pw.lumm.controller;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pw.lumm.model.*;
import pw.lumm.service.inf.ForumService;
import pw.lumm.service.inf.QuestionService;
import pw.lumm.service.inf.SearchService;
import pw.lumm.service.inf.UserService;
import pw.lumm.utils.FastDFSClient;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by LM on 2018/4/21.
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {
    private String File_SERVER_URL = "http://192.168.202.200/";
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String toIndex(@RequestParam String fid, Model model) throws Exception {
        List<Question> questions = questionService.getQuestionByForunmId(fid);
        Forum forum = forumService.getFourmById(fid);
        User user = userService.getUserById(forum.getUid());
        ForumExample forumExample = new ForumExample();
        forumExample.setForum(forum);
        forumExample.setQuestions(questions);
        forumExample.setUser(user);
        model.addAttribute("forumExample", forumExample);
        if(forum.getType()==1){
            return "question/index";
        }
        return "question/emotionlist";
    }


    @RequestMapping("/add")
    public String toAdd(String id, Model model) throws Exception {
        Forum forum = forumService.getFourmById(id);
        model.addAttribute("forum", forum);
        return "question/add";
    }

    @RequestMapping("/toAddquestion")
    public String toAddquestion() throws Exception {
       /* Forum forum = forumService.getFourmById(id);
        model.addAttribute("forum", forum);*/
        return "answer/add";
    }


    @RequestMapping("/addForum")
    public void addForum(HttpServletResponse out, String loginid, int type, String ftitle, String fcontent) throws Exception {
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

    /*@RequestMapping("/addQuestion")
    public void addQuestion(HttpServletResponse out, String loginid, String fid, String title, String value_content,MultipartFile filecontent) throws IOException {
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
    }*/
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public void addQuestion(HttpServletResponse out, String loginid, String fid, String title, String content, MultipartFile filecontent) throws IOException {
        String filepath = "";
        String originalFilename = "";
        try {
            originalFilename = filecontent.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (extName != null&&extName!="") {
                FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
                String path = fastDFSClient.uploadFile(filecontent.getBytes(), extName);
                filepath = File_SERVER_URL + path;
                questionService.addQuestion(loginid, fid, title, content, filepath, originalFilename);
                response.Status = true;
            }else {
                questionService.addQuestion(loginid, fid, title, content, null, null);
                response.Status = true;
            }
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
        }
        out.getWriter().write(gson.toJson(response));
    }

    @RequestMapping("/detail")
    public String toDetail(@RequestParam String id, Model model) throws Exception {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = questionService.getAnswerByQid(id);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setAnswers(answers);
        questionExample.setQuestion(question);

        model.addAttribute("questionExample", questionExample);
        return "question/detail";
    }

    @RequestMapping("/toemotion")
    public String toemotion(@RequestParam int type, Model model) throws Exception {
        QuestionExample questionExample = new QuestionExample();
        List<Forum> forumList = forumService.getForum(type, 1);

        questionExample.setForums(forumList);
        if (forumList != null && forumList.size() > 0) {
            for (int i = 0; i < forumList.size(); i++) {
                List<Question> questions = questionService.getQuestionByForunmId(forumList.get(i).getFid());
                questionExample.setQuestions(questions);
            }
        }
        model.addAttribute("questionExample", questionExample);
        return "question/emotionindex";
    }

    @RequestMapping("/setForumStatus")
    public void setForumStatus(HttpServletResponse out, int status, String id) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setForumStatus(id, status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }

    @RequestMapping("/setTop")
    public void setTop(HttpServletResponse out, String id, int status) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setTop(id, status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }

    @RequestMapping("/setStatus")
    public void setStatus(HttpServletResponse out, String id, int status) throws IOException {


        out.setContentType("text/html; charset=utf-8");
        try {
            questionService.setStatus(id, status);
            response.Status = true;
        } catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            System.out.println(e.getMessage());
        }
        out.getWriter().write(gson.toJson(response));


    }

    @RequestMapping("/delete")
    public void deleteByQid(HttpServletResponse out, String id) throws IOException {

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

    @RequestMapping(value = "/upimg", method = RequestMethod.POST)
    public void imgUpload(@RequestParam MultipartFile uploadFile, HttpServletResponse out) {
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String url = File_SERVER_URL + path;
            //out.getWriter().write(gson.toJson(url));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/downFile")
    public void downFile(@RequestParam String filename) {
        try {

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            fastDFSClient.downFile(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
