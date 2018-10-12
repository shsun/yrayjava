package spring.cloud.engine.dataaccess.mapper;

import spring.cloud.engine.dataaccess.dataobject.ZMomentDo;

public interface ZMomentDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZMomentDo record);

    int insertSelective(ZMomentDo record);

    ZMomentDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZMomentDo record);

    int updateByPrimaryKeyWithBLOBs(ZMomentDo record);

    int updateByPrimaryKey(ZMomentDo record);
}