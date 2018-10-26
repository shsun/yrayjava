package spring.cloud.dsp.controller;

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
//import spring.cloud.gateway.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import base.BConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Api(description = "用户信息相关接口")
public class AccountController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    // @Autowired
    //private AccountService accountService;

    @ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息，userId\n\r\t" + "不传userId的话，默认取当前用户的信息")
    @RequestMapping(path = "/account/detail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel<AccountModel> detail(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "userId", required = false) String userId) {

        System.out.println(BConstants.ABC);

        //return this.accountService.detailByUserId(userId);
        return null;
    }

    @RequestMapping(value = "/index2", method = {RequestMethod.POST, RequestMethod.GET})
    public String index2(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/home/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
        return this.login(request, response, null, "gogogo", "123456");
    }

    @RequestMapping(value = "/account/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model, String userName, String password) {

        System.out.println(BConstants.ABC);

        //ResultModel<String> rst = this.accountService.login(response, userName, password);

        // return rst;

        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", "gogog");
        List<Map<String, String>> queryset = new ArrayList<>();
        Map<String, String> qury = new HashMap<>();
        qury.put("measurement_date", "2018-10-10");
        qury.put("comments", "comments__");
        queryset.add(qury);

        mv.addObject("queryset", queryset);
        mv.addObject("cz_all_money", 123);
        mv.addObject("sy_release_money", 123);
        mv.addObject("sy_gift_money", 123);
        mv.addObject("impression__sum", 123);
        mv.addObject("click__sum", 123);
        mv.addObject("click_ctr", 123);
        mv.addObject("cost__sum", 123);

        mv.setViewName("home");
        return mv;
    }


    @RequestMapping(value = "/recharge_consumption/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> recharge_consumption(HttpServletRequest request, HttpServletResponse response) {

        List<Map<String, Object>> X_data = new ArrayList();
        /*
        for (int i = 0; i < 10; i++) {
            Map<String, Object> y = new HashMap<>();
            y.put("data", 3 * i);
            X_data.add(y);
        }
        */
//
//        cost_data_list = [
//        {'date': x_dict.get('date').strftime('%Y-%m-%d'), 'advertiser': x_dict.get('advertiser'),
//                'total_cost': round(x_dict.get('total_cost'), 1)} for x_dict in cost_data_list]

        return X_data;
    }

    @RequestMapping(value = "/pager1/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> pager1(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> y = new HashMap<>();
        y.put("results", new ArrayList<>());
        y.put("count", 0);
        return y;
    }

    @RequestMapping(value = "/advertiser_hour/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> advertiser_hour(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> X_data = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> y = new HashMap<>();
            y.put("data", 3 * i);
            X_data.add(y);
        }


        List<Map<String, Object>> Y_data = new ArrayList();
        Map<String, Object> impression_dict = new HashMap<>();
        impression_dict.put("name", "曝光数");
        impression_dict.put("type", "line");
        impression_dict.put("stack", "总量");
        impression_dict.put("data", new ArrayList<>());
        Y_data.add(impression_dict);
        Map<String, Object> y = new HashMap<>();
        y.put("data", 3);
        Y_data.add(y);


        List<Map<String, Object>> data_list = new ArrayList();
        Map<String, Object> d1 = new HashMap<>();
        d1.put("X_data", X_data);
        d1.put("Y_data", Y_data);
        data_list.add(d1);
        return data_list;
    }


    @RequestMapping(value = "/h5/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView h5(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(BConstants.ABC);

        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", "userName-ggogo");
        mv.addObject("user_id", "userid----onono");
        // return render(request, 'black5.html', {'username': request.user, 'user_id': request.user.id})
        mv.setViewName("black5");
        return mv;

    }


    @RequestMapping(value = "/recharge_html/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView recharge_html(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("userName", "userName-ggogo");
        mv.addObject("user_id", "userid----onono");

        // return render(request, 'finanace.html', {'username': request.user, 'user_id': request.user.id})
        mv.setViewName("finanace");
        return mv;
    }


    @RequestMapping(value = "/adver/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView adver_content(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        //return render(request, 'adver_content.html', {'username': request.user, 'user_id': request.user.id, 'all_ad_campaing': all_ad_campaing})

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        mv.addObject("userName", "userName-ggogo");
        mv.addObject("user_id", "userid----onono");
        mv.addObject("all_ad_campaing", list);

        // return render(request, 'finanace.html', {'username': request.user, 'user_id': request.user.id})
        mv.setViewName("adver_content");
        return mv;
    }
}