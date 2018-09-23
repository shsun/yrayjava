package spring.cloud.biz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.cloud.biz.config.datasourceConfig.DataSourceType;
import spring.cloud.biz.config.datasourceConfig.TargetDataSource;
import spring.cloud.biz.service.MomentService;
import spring.cloud.client.model.MomentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Harry on 15/12/2017.
 */
@RestController
@RequestMapping("/moment")
@TargetDataSource(DataSourceType.MOMENT)        //using the moment DataSource
public class MomentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MomentController.class);

    @Autowired
    private MomentService momentService;

    @GetMapping("/list")
    public ListResultModel<MomentModel> listFirstPageMoment(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        page = page <= 0 ? 1 : page;
        pageSize = pageSize <= 0 || pageSize > 50 ? 10 : pageSize;

        return this.momentService.listFirstPageMoment(page, pageSize);
    }

    @PostMapping("")
    public ResultModel<MomentModel> addMoment(HttpServletRequest request, HttpServletResponse response, @RequestParam String userId, @RequestParam String content) {
        return this.momentService.addMoment(userId, content);
    }

}
