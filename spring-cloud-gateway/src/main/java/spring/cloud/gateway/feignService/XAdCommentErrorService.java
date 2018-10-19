package spring.cloud.gateway.feignService;

import org.springframework.stereotype.Component;
import spring.cloud.client.model.CommentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

@Component
public class XAdCommentErrorService implements CommentFeignService {
    @Override
    public ListResultModel<CommentModel> listCommentsByMomentId(Long momentId) {
        return null;
    }

    @Override
    public ResultModel<CommentModel> addComment(Long momentId, String content) {
        return null;
    }
}
