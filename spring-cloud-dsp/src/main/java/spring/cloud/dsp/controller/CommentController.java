package spring.cloud.dsp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.cloud.dsp.config.datasourceConfig.DataSourceType;
import spring.cloud.dsp.config.datasourceConfig.TargetDataSource;
import spring.cloud.dsp.dataaccess.dataobject.ZCommentDo;
import spring.cloud.dsp.service.CommentService;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

@RestController
@RequestMapping("/comment")
@TargetDataSource(DataSourceType.COMMENT)        //using the moment DataSource
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping("/{momentId}/list")
    public ListResultModel<ZCommentDo> listCommentsByMomentId(HttpServletRequest request, HttpServletResponse response, @PathVariable Long momentId) {
        ListResultModel<ZCommentDo> rst = this.commentService.listCommentsByMomentId(momentId);
        return rst;
    }

    @PostMapping("/{momentId}/add")
    public ResultModel<ZCommentDo> addComment(HttpServletRequest request, HttpServletResponse response, @PathVariable Long momentId, @RequestParam String content) {
        ResultModel<ZCommentDo> rst = this.commentService.addComment(momentId, content);
        return rst;
    }
}
