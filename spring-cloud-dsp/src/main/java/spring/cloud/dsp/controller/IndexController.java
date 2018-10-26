package spring.cloud.dsp.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.cloud.dsp.MessageException;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.dsp.dataaccess.dataobject.ZUserEntry;
import spring.cloud.dsp.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CommentService commentService;


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

    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping("/user/ulogin")
    public ResultModel<String> login(HttpServletRequest request, HttpServletResponse response, ZUserEntry userEntry) {
//        ResultModel<ZCommentDo> rst = this.commentService.addComment(momentId, content);
//        return rst;


        LOGGER.info("login");

        return null;
    }


    @RequestMapping(value = "/user/ulogin2", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultModel<String> login2(HttpServletRequest request, HttpServletResponse response) {
//        ResultModel<ZCommentDo> rst = this.commentService.addComment(momentId, content);
//        return rst;


        LOGGER.info("login2");

        if (1 == 1)
            throw new MessageException(1432, "Sam 错误");

        return null;
    }
}