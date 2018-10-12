package spring.cloud.ssp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyHttpSessionListener
 * 通过  @WebListener 或者 使用代码注册  ServletListenerRegistrationBean
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDataContextListener.class);

    /**
     * Default constructor.
     */
    public MyHttpSessionListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        LOGGER.info(event.getSession().getId() + " session create");
    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        LOGGER.info(event.getSession().getId() + " session destroy");
    }

}

