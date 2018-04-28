package pw.lumm.utils.listeners;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pw.lumm.model.News;
import pw.lumm.model.NewsExample;
import pw.lumm.model.Notic;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.NewsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class NewsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {

      /*  ServletContext sc=event.getServletContext();
        WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);
        NewsService newsService = (NewsService) ctx.getBean("newsService");
        MessageService messageService = (MessageService) ctx.getBean("messageService");*/

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
