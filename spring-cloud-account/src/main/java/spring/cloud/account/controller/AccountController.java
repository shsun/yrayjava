package spring.cloud.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import base.BConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import spring.cloud.account.service.AccountService;
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

@RestController
@RequestMapping("/account")
@Api(description = "用户信息相关接口")
public class AccountController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "获取用户详细信息")
    @GetMapping("/detail")
    public ResultModel<AccountModel> detailByUserId(HttpServletRequest request, HttpServletResponse response, @RequestParam("userName") String userName) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountController.detailByUserId() userName=" + userName);

        ResultModel<AccountModel> rst = this.accountService.detail(userName);
        return rst;
    }

    @PostMapping("/validate")
    public ResultModel<String> validateUserIdAndPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam("userName") String userName,
            @RequestParam("password") String password) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountController.validateUserIdAndPassword() userid=" + userName + ", password=" + password);

        ResultModel<String> rst = this.accountService.validateUserIdAndPassword(userName, password);
        return rst;
    }

}