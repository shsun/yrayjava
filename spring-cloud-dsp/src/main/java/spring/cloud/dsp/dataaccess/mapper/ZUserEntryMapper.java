package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZUserEntry;

public interface ZUserEntryMapper {
    int deleteByPrimaryKey(String userId);

    int insert(ZUserEntry record);

    int insertSelective(ZUserEntry record);

    ZUserEntry selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(ZUserEntry record);

    int updateByPrimaryKey(ZUserEntry record);
}