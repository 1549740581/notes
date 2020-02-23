package nwpu.sherman.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * DynamicListener：注意这个Listener是动态注册的，没有@WebListener注解
 *
 * @author sherman
 */
public class DynamicListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("DynamicListener...contextInitialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
