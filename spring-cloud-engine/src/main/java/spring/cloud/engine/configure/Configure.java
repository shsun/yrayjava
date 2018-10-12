package spring.cloud.engine.configure;


import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cloud.engine.filter.LoginFilter;
import spring.cloud.engine.listener.MyHttpSessionListener;

/**
 * @author shsun
 */
@Configuration
public class Configure {

//    @Bean
//    public ServletListenerRegistrationBean<MyHttpSessionListener> serssionListenerBean() {
//        return new ServletListenerRegistrationBean<MyHttpSessionListener>(new MyHttpSessionListener());
//    }

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        // 配置无需过滤的路径或者静态资源，如：css，imgage等
        StringBuffer excludedUriStr = new StringBuffer();
        excludedUriStr.append("/login/*");
        excludedUriStr.append(",");
        excludedUriStr.append("/favicon.ico");
        excludedUriStr.append(",");
        excludedUriStr.append("/js/*");

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("excludedUri", excludedUriStr.toString());
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }
}


