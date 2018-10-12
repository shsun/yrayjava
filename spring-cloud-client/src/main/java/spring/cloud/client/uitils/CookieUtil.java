package spring.cloud.client.uitils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class CookieUtil {
    /**
     * @param response
     * @param name
     * @param value
     * @param maxage
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxage) {
        addCookie(response, name, value, null, maxage, "/");
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param maxage
     * @param path
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxage, String path) {
        addCookie(response, name, value, null, maxage, path);
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param maxage   最长存活时间 单位为秒
     * @param path
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, int maxage, String path) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxage);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 往根下面存一个cookie
     *
     * @param name     cookie的key
     * @param value    cookie的value
     * @param domain   domain
     * @param maxage   最长存活时间 单位为秒
     * @param response
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, int maxage) {
        addCookie(response, name, value, domain, maxage, "/");
    }

    /**
     * 从cookie值返回cookie值，如果没有返回 null
     *
     * @param request
     * @param name
     * @return cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        String rst = null;
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            rst = cookie.getValue();
        }
        return rst;
    }


    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie rst = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    rst = cookies[i];
                    break;
                }
            }
        }
        return rst;
    }

    /**
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        removeCookie(response, name, null);
    }

    /**
     * @param response
     * @param name
     * @param domain
     */
    public static void removeCookie(HttpServletResponse response, String name, String domain) {
        addCookie(response, name, null, domain, 0);
    }
}
