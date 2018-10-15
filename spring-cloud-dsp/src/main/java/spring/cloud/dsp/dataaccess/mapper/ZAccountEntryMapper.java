package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZAccountEntry;

public interface ZAccountEntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZAccountEntry record);

    int insertSelective(ZAccountEntry record);

    ZAccountEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZAccountEntry record);

    int updateByPrimaryKey(ZAccountEntry record);
}