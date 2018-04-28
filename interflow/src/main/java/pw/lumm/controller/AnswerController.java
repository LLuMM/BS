package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.service.inf.AnsewerServer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/answer")
public class AnswerController extends BaseController{
    @Autowired
    AnsewerServer ansewerServer;

    @RequestMapping("/addAnswer")
    public void addAnswer(HttpServletResponse out, String uid, String id, String content, String username) throws IOException {


        out.setContentType("text/html; charset=utf-8");
        try {
            ansewerServer.addAnswer(uid,id,content,username);
            response.Status = true;
        }catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            e.printStackTrace();

        }
        out.getWriter().write(gson.toJson(response));


    }
    @RequestMapping("/addAnswerToUser")
    public void addAnswerToUser(HttpServletResponse out,String qid, String fromid, String toid,String content) throws IOException {
        out.setContentType("text/html; charset=utf-8");
        try {
            ansewerServer.addAnswerToUser(qid, fromid,toid, content);
            response.Status = true;
        }catch (Exception e) {
            response.Status = false;
            response.Message = e.getMessage();
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));



    }

}
