package spring.cloud.biz.service.impl;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.cloud.biz.config.datasourceConfig.DataSourceType;
import spring.cloud.biz.config.datasourceConfig.DynamicDataSourceContextHolder;
import spring.cloud.biz.dataaccess.dataobject.CommentDo;
import spring.cloud.biz.dataaccess.dataobject.MomentDo;
import spring.cloud.biz.dataaccess.dataobject.ZCommentDo;
import spring.cloud.biz.dataaccess.dataobject.ZMomentDo;
import spring.cloud.biz.dataaccess.mapper.CommentDoMapper;
import spring.cloud.biz.dataaccess.mapper.MomentDoMapper;
import spring.cloud.biz.service.CommentService;
import spring.cloud.client.model.CommentModel;
import spring.cloud.client.uitils.CopyProperityUtils;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.demo.model.TraceIdHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 16/12/2017.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDoMapper commentDoMapper;

    @Autowired
    private MomentDoMapper momentDoMapper;

    @Override
    @Transactional(value = "commentTxManager", propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, Throwable.class})
    public ListResultModel<ZCommentDo> listCommentsByMomentId(Long momentId) {
        ListResultModel<ZCommentDo> resultModel = ListResultModel.createSuccess();
        if (null == momentId) {
            LOGGER.error("traceId:{}, listCommentsByMomentId, momentId is null", TraceIdHelper.getTraceId());
            return resultModel;
        }

        List<ZCommentDo> commentDoList = this.commentDoMapper.listCommentsByMomentId(momentId);
        if (null == commentDoList || commentDoList.isEmpty()) {
            return resultModel;
        }
        List<ZCommentDo> modelList = new ArrayList<>();
        for (ZCommentDo commentDo : commentDoList) {
            ZCommentDo commentModel = new ZCommentDo();
            CopyProperityUtils.copyAllProperies(commentDo, commentModel);
            modelList.add(commentModel);
        }
        resultModel.setData(modelList);

        return resultModel;
    }

    @Override
    public ResultModel<ZCommentDo> addComment(Long momentId, String content) {
        if (null == momentId || Strings.isNullOrEmpty(content)) {
            LOGGER.error("traceId:{} addComment fail, invalidParam, momentId:{}, content:{}",
                    TraceIdHelper.getTraceId(), momentId, content);
            return ResultModel.createFail("invalidParam");
        }

        //set which dataSource to use
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MOMENT);
        ZMomentDo momentDo = this.momentDoMapper.selectByPrimaryKey(momentId);
        if (null == momentDo) {
            LOGGER.error("traceId:{}, addComment fail, momentId:{} does not exist, content:{}",
                    TraceIdHelper.getTraceId(), momentId, content);
            return ResultModel.createFail("noThisMoment");
        }

        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.COMMENT);
        ZCommentDo commentDo = new ZCommentDo();
        commentDo.setMomentId(momentId);
        commentDo.setContent(content);
        commentDo.setGmtCreated(new Date());
        commentDo.setGmtModified(new Date());
        commentDo.setIsDeleted(false);
        this.commentDoMapper.insert(commentDo);

        ZCommentDo commentModel = new ZCommentDo();
        CopyProperityUtils.copyAllProperies(commentDo, commentModel);

        return ResultModel.createSuccess(commentModel);
    }
}
