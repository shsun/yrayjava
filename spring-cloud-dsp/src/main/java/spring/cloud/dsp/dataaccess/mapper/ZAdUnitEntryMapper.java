package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZAdUnitEntry;

public interface ZAdUnitEntryMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(ZAdUnitEntry record);

    int insertSelective(ZAdUnitEntry record);

    ZAdUnitEntry selectByPrimaryKey(String unitId);

    int updateByPrimaryKeySelective(ZAdUnitEntry record);

    int updateByPrimaryKey(ZAdUnitEntry record);
}