package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZCommentDo;

public interface ZCommentDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZCommentDo record);

    int insertSelective(ZCommentDo record);

    ZCommentDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZCommentDo record);

    int updateByPrimaryKeyWithBLOBs(ZCommentDo record);

    int updateByPrimaryKey(ZCommentDo record);
}