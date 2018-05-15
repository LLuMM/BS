package pw.lumm.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageInterceptor extends HandlerInterceptorAdapter {
   /*   @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       System.out.println("===========HandlerInterceptor1 preHandle");
          System.out.println(request.getServletPath());
        return true;
    }*/
}
