package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZAdMetricEntry;

public interface ZAdMetricEntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZAdMetricEntry record);

    int insertSelective(ZAdMetricEntry record);

    ZAdMetricEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZAdMetricEntry record);

    int updateByPrimaryKey(ZAdMetricEntry record);
}