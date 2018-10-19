package spring.cloud.gateway.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.gateway.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import base.BConstants;

import java.util.ArrayList;
import java.util.List;


@Controller
@Api(description = "用户信息相关接口")
public class AccountController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息，userId\n\r\t" + "不传userId的话，默认取当前用户的信息")
    @RequestMapping(path = "/account/detail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel<AccountModel> detail(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "userId", required = false) String userId) {

        System.out.println(BConstants.ABC);

        return this.accountService.detailByUserId(userId);
    }

    @RequestMapping(value = "/index2", method = {RequestMethod.POST, RequestMethod.GET})
    public String index2(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        return "login";
    }


    @RequestMapping(value = "/account/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model, String userName, String password) {

        System.out.println(BConstants.ABC);

        ResultModel<String> rst = this.accountService.login(response, userName, password);

       // return rst;



/*

        return render(request, 'home.ftl',
                {'queryset': queryset, 'username': request.user, 'user_id': request.user.id,
                'sy_release_money': sy_release_money, 'sy_gift_money': sy_gift_money,
                'cz_all_money': cz_all_money, 'cost__sum': cost__sum,
                'impression__sum': impression__sum,
                'click__sum': click__sum, 'click_ctr': click_ctr})

    */

        ModelAndView mv = new ModelAndView();
        mv.addObject("'username'", "gogog");
        mv.setViewName("home");
        return mv;

    }

}