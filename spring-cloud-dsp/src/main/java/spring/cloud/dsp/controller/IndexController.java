package spring.cloud.dsp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.dsp.config.datasourceConfig.DataSourceType;
import spring.cloud.dsp.config.datasourceConfig.TargetDataSource;
import spring.cloud.dsp.dataaccess.dataobject.ZCommentDo;
import spring.cloud.dsp.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        List<String> userList = new ArrayList<String>();
        userList.add("admin");
        userList.add("user1--");
        userList.add("user2--");

        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
}