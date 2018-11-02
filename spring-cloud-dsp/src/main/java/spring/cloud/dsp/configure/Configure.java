package spring.cloud.dsp.configure;


import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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

    /*
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
    */


    @Autowired
    freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {

        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
    }
}


