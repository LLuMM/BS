package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.lumm.service.inf.MessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/msg")
public class MessageController extends BaseController{

    @Autowired
    MessageService messageService;
    @RequestMapping("addNotic")
    public void addNotic(HttpServletResponse out, String notic) throws IOException {
        try{
            System.out.println(notic);
            messageService.addNotic(notic);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            response.Message = "有误！";
        }
        out.getWriter().write(gson.toJson(response));
    }
}
