package spring.cloud.dsp.dataaccess.dataobject;

import java.io.Serializable;
import java.util.Date;

public class ZAdMetricEntry implements Serializable {
    private Integer id;

    private Date ngxDate;

    private String adSystem;

    private String slotId;

    private String campaignId;

    private String unitId;

    private String eventType;

    private Integer num;

    private Date gmtCreated;

    private Date gmtModified;

    private Boolean isDeleted;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgxDate() {
        return ngxDate;
    }

    public void setNgxDate(Date ngxDate) {
        this.ngxDate = ngxDate;
    }

    public String getAdSystem() {
        return adSystem;
    }

    public void setAdSystem(String adSystem) {
        this.adSystem = adSystem == null ? null : adSystem.trim();
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId == null ? null : slotId.trim();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId == null ? null : campaignId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}