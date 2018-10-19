package spring.cloud.pusher.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.cloud.demo.cache.CacheService;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.pusher.config.datasourceConfig.DataSourceType;
import spring.cloud.pusher.config.datasourceConfig.TargetDataSource;
import spring.cloud.pusher.dataaccess.dataobject.ZCommentDo;
import spring.cloud.pusher.quartz.QuartzManager;
import spring.cloud.pusher.quartz.XAdCampaign2RedisJob;
import spring.cloud.pusher.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apk")
public class XAdAPKDowningController {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAdAPKDowningController.class);


    @Autowired
    QuartzManager quartzManager;

    @GetMapping("/retrieve")
    public ListResultModel<ZCommentDo> listCommentsByMomentId(HttpServletRequest request, HttpServletResponse response) {


        return null;
    }

    @GetMapping("/b")
    public String addComment(HttpServletRequest request, HttpServletResponse response) {

        String jobName = "动态任务调度";
        String jobGroupName = "任务组名";
        String triggerName = "触发器名";
        String triggerGroupName = "触发器组名";
        String time = "*/5 * * * * ?";
        quartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, XAdCampaign2RedisJob.class, time);
        return "xxx";
    }
}
