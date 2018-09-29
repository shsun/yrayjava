package spring.cloud.gateway.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(description = "freemarker demo")
public class HomeController {


    @RequestMapping(value = "/a", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Resource resource = new Resource("shsun_index", "https://shsun.github.io", "cn");
        map.addAttribute("resource", resource);
        return "views/index";
    }

    @RequestMapping(value = "/b", method = {RequestMethod.POST, RequestMethod.GET})
    public String home(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Resource resource = new Resource("shsun_welcome", "https://shsun.github.io", "cn");
        map.addAttribute("resource", resource);
        return "views/welcome";
    }
}