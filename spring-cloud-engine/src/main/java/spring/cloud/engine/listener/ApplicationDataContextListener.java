package spring.cloud.engine.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import spring.cloud.engine.controller.CommentController;

/**
 * @author shsun
 */
public class ApplicationDataContextListener extends ContextLoaderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDataContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        LOGGER.info("contextInitialized");
    }
}
