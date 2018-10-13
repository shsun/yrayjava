package spring.cloud.dsp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import spring.cloud.dsp.config.interceptors.GlobalAspectInteceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/login/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);


    private String[] excludedUris;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedUris = filterConfig.getInitParameter("excludedUri").split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        LOGGER.info("this is LoginFilter,url :" + request.getRequestURI());

        String uri = request.getServletPath();
        // FIXME
        /*
        if (isExcludedUri(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (request.getSession().getAttribute("user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "/login/toLogin");
        }
        */
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isExcludedUri(String uri) {
        if (excludedUris == null || excludedUris.length <= 0) {
            return false;
        }
        for (String ex : excludedUris) {
            uri = uri.trim();
            ex = ex.trim();
            if (uri.toLowerCase().matches(ex.toLowerCase().replace("*", ".*")))
                return true;
        }
        return false;
    }
}