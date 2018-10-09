package spring.cloud.biz.dataaccess.mapper;

import org.apache.ibatis.annotations.Select;
import spring.cloud.biz.dataaccess.dataobject.CommentDo;
import spring.cloud.biz.dataaccess.dataobject.ZCommentDo;

import java.util.List;

public interface CommentDoMapper extends ZCommentDoMapper {

    @Select("select * from comment where moment_id=#{momentId,jdbcType=BIGINT}")
    List<ZCommentDo> listCommentsByMomentId(Long momentId);
}