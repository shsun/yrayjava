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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @RequestMapping(value = "/home/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
        return this.login(request, response, null, "gogogo", "123456");
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

        /*
                    // FIXME 需要返回数据准备
            // X轴时间数据
        X_data = []
            // Y轴数据
        Y_data = []
        impression_dict = {'name': '曝光数', 'type': 'line', 'stack': '总量', 'data': []}
        click_dict = {'name': '点击数', 'type': 'line', 'stack': '总量', 'data': []}
        ctr_dict = {'name': '点击率', 'type': 'line', 'stack': '总量', 'data': []}
        cost_dict = {'name': '花费', 'type': 'line', 'stack': '总量', 'data': []}

        day_number = int(request.GET.get('day_number', 0))

        app_account_entry = App_Account_Entry()

        if day_number == 0:
        year, month, day = Public_Function().get_current_year_month_day()
        hour_account_data = Hour_Account_AmountService().retrieve_hour_account_amount_by_advertiser_id_day(
                p_user_id=request.user.id, year=year, month=month, day=day)

        for ele in hour_account_data:
        X_data.append((ele.date).strftime("%Y-%m-%d %H:%M:%S"))
        impression_dict['data'].append(ele.impression)
        click_dict['data'].append(ele.click)
        ctr_dict['data'].append(ele.ctr)
        cost_dict['data'].append(ele.cost)

        if day_number == 1:
        yesterday = Public_Function().get_yesterday_day()
        yesterday = yesterday.split('-')
        year = yesterday[0]
        month = yesterday[1]
        day = yesterday[2]

        hour_account_data = Hour_Account_AmountService().retrieve_hour_account_amount_by_advertiser_id_day(
                p_user_id=request.user.id, year=year, month=month, day=day)

        for ele in hour_account_data:
        X_data.append((ele.date).strftime("%Y-%m-%d %H:%M:%S"))
        impression_dict['data'].append(ele.impression)
        click_dict['data'].append(ele.click)
        ctr_dict['data'].append(ele.ctr)
        cost_dict['data'].append(ele.cost)

        if day_number == 7:
        age_7 = Public_Function().get_day_jian(Public_Function().get_current_date(), 7)
        row = Hour_Account_AmountService().retrieve_hour_account_amount_by_advertiser_id_day_impression_click_cost(
                age_7=age_7, p_user_id=request.user.id)

        for i_tuple in row:
        X_data.append(i_tuple[1].strftime("%Y-%m-%d"))
        impression_dict['data'].append(int(i_tuple[2]))
        click_dict['data'].append(int(i_tuple[3]))
        ctr_dict['data'].append(round(int(i_tuple[3]) / int(i_tuple[2]), 2))
        cost_dict['data'].append(round(int(i_tuple[4]), 2))

        Y_data.append(impression_dict)
        Y_data.append(click_dict)
        Y_data.append(ctr_dict)
        Y_data.append(cost_dict)
        data_list = []

        Map<String, Object> d1 = new HashMap<>();
        d1.put("'X_data'", X_data);
        d1.put("'Y_data'", Y_data);
        data_list.append(d1);
        */




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


}