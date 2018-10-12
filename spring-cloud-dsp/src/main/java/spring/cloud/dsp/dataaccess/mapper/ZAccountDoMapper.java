package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZAccountDo;

public interface ZAccountDoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(ZAccountDo record);

    int insertSelective(ZAccountDo record);

    ZAccountDo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(ZAccountDo record);

    int updateByPrimaryKey(ZAccountDo record);
}