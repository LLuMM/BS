package pw.lumm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.lumm.model.Msg;
import pw.lumm.service.inf.MessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/msg")
public class MessageController extends BaseController{

    @Autowired
    MessageService messageService;
    @RequestMapping("/addNotic")
    public void addNotic(HttpServletResponse out, String notic) throws IOException {
        try{
            messageService.addNotic(notic);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            response.Message = "有误！";
        }
        out.getWriter().write(gson.toJson(response));
    }
    @RequestMapping("/deleteNotice")
    public void deleteNotice(HttpServletResponse out, String id) throws IOException {
        try{
            messageService.deleteNotice(id);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            response.Message = "有误！";
        }
        out.getWriter().write(gson.toJson(response));
    }

    /*
     * 删除所有已读信息(参数：用户id)
     * */
    @RequestMapping("/deleteByStatus")
    public void deleteByStatus(@RequestParam String id)  {
        try {
            messageService.deleteByStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @RequestMapping("/deleteMsgById")
    public void deleteMsgById(HttpServletResponse out, String id) throws IOException {
        try{
            messageService.deleteMsgById(id);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            response.Message = "有误！";
        }
        out.getWriter().write(gson.toJson(response));
    }
    @RequestMapping("/setMsgStatus")
    public void setMsgStatus(HttpServletResponse out, String id) throws IOException {
        try{
            Msg msg = new Msg();
            msg.setId(id);
            messageService.setMessasgeStatus(msg);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            response.Message = "有误！";
        }
        out.getWriter().write(gson.toJson(response));
    }
    @RequestMapping("/chatMessage")
    public void chatMessage(HttpServletResponse out,String fromid, String toid) throws IOException {
        try{

            messageService.chatMessage(fromid,toid);
            response.Status = true;
        }catch (Exception e){
            response.Status = false;
            e.printStackTrace();
        }
        out.getWriter().write(gson.toJson(response));
    }

}
