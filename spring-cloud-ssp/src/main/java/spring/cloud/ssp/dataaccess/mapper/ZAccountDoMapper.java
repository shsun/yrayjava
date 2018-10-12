package spring.cloud.ssp.dataaccess.mapper;

import spring.cloud.ssp.dataaccess.dataobject.ZAccountDo;

public interface ZAccountDoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(ZAccountDo record);

    int insertSelective(ZAccountDo record);

    ZAccountDo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(ZAccountDo record);

    int updateByPrimaryKey(ZAccountDo record);
}