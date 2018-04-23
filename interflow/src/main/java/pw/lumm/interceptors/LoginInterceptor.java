package pw.lumm.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
   /* @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       System.out.println("===========HandlerInterceptor1 preHandle");
        if (request.getSession().getAttribute("user")!=null){
            return true;
        }
        return false;
    }*/

}
