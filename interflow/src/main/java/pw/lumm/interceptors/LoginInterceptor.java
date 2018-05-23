package pw.lumm.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pw.lumm.model.User;
import pw.lumm.service.inf.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;
    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       System.out.println("===========d登陆");
        if (request.getSession().getAttribute("user")==null){
            response.sendError(600);
            return false;
        }else {
            return true;
        }
    }

}
