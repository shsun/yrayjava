package spring.cloud.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.cloud.client.model.CommentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.gateway.feignService.CommentFeignService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Harry on 15/12/2017.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentFeignService commentFeignService;

    @GetMapping("/{momentId}/list")
    public String listCommentsByMomentId(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable Long momentId) {
        ListResultModel<CommentModel> commentModelListResultModel = this.commentFeignService.listCommentsByMomentId(momentId);
        model.addAttribute("commentList", commentModelListResultModel.getData());
        model.addAttribute("momentId", momentId);

        return "commentList";
    }

    @PostMapping("/{momentId}/add")
    @ResponseBody
    public ResultModel<CommentModel> addComment(HttpServletRequest request, HttpServletResponse response, @PathVariable Long momentId, @RequestParam String content) {
        ResultModel<CommentModel> rst = this.commentFeignService.addComment(momentId, content);
        return rst;
    }
}
