package spring.cloud.ssp.service;

import spring.cloud.ssp.dataaccess.dataobject.ZCommentDo;
import spring.cloud.ssp.dataaccess.dataobject.ZMomentDo;
import spring.cloud.client.model.CommentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

/**
 * Created by Harry on 16/12/2017.
 */
public interface CommentService {
    /**
     * retrieve comment by id
     *
     * @param momentId
     * @return
     */
    ListResultModel<ZCommentDo> listCommentsByMomentId(Long momentId);

    /**
     * add comment
     *
     * @param momentId
     * @param content
     * @return
     */
    ResultModel<ZCommentDo> addComment(Long momentId, String content);
}
