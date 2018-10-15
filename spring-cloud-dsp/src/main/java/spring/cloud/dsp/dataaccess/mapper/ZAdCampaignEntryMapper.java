package spring.cloud.dsp.dataaccess.mapper;

import spring.cloud.dsp.dataaccess.dataobject.ZAdCampaignEntry;

public interface ZAdCampaignEntryMapper {
    int deleteByPrimaryKey(String campaignId);

    int insert(ZAdCampaignEntry record);

    int insertSelective(ZAdCampaignEntry record);

    ZAdCampaignEntry selectByPrimaryKey(String campaignId);

    int updateByPrimaryKeySelective(ZAdCampaignEntry record);

    int updateByPrimaryKey(ZAdCampaignEntry record);
}