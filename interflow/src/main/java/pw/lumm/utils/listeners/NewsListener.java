package pw.lumm.utils.listeners;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pw.lumm.service.inf.SearchService;
import pw.lumm.utils.thrift.NewsFresh;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class NewsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try{
            NewsFresh newsFresh = new NewsFresh();
            newsFresh.getHotNews();
            ServletContext sc=event.getServletContext();
            WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);
            SearchService searchService = (SearchService) ctx.getBean("searchService");
            searchService.deleteAllNews();
            searchService.importAllTitle();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("thrift服务未开或搜索服务没启动！");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
