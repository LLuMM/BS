package pw.lumm.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pw.lumm.model.User;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    MessageService messageService;
    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

          if (session.getAttribute("user")!=null&&session.getAttribute("user")!=""){
              User user= (User) session.getAttribute("user");
            if (messageService.getMessageByUidWithStatus(user.getUid())){
                session.setAttribute("msg",true);
            }else
                session.setAttribute("msg",false);
          }
       /*System.out.println("===========HandlerInterceptor1 preHandle");
          System.out.println(request.getServletPath());*/
        return true;
    }
}
