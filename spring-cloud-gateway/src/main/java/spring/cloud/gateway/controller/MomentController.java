package spring.cloud.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.cloud.client.model.MomentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.gateway.config.AccountHelper;
import spring.cloud.gateway.feignService.MomentFeignService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Harry on 15/12/2017.
 */
@Controller
@RequestMapping("/moment")
public class MomentController {

    @Autowired
    private MomentFeignService momentFeignService;

    @GetMapping("/list")
    public String listFirstPageMoment(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        page = page <= 0 ? 1 : page;
        pageSize = pageSize <= 0 || pageSize > 50 ? 10 : pageSize;

        ListResultModel<MomentModel> momentList = this.momentFeignService.listFirstPageMoment(page, pageSize);

        model.addAttribute("momentList", momentList.getData());
        return "momentList";
    }

    @PostMapping("")
    @ResponseBody
    public ResultModel<MomentModel> addMoment(HttpServletRequest request, HttpServletResponse response, @RequestParam String content) {
        String userId = AccountHelper.getUserId();
        return this.momentFeignService.addMoment(userId, content);
    }

}
