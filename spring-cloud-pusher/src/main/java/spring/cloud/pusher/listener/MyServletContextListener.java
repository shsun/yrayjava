package spring.cloud.pusher.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 */

/**
 * 通过  @WebListener 或者 使用代码注册  ServletListenerRegistrationBean
 *
 * @author shsun
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.info(event.getServletContext().getServletContextName() + " init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info(event.getServletContext().getServletContextName() + " destroy");
    }
}

