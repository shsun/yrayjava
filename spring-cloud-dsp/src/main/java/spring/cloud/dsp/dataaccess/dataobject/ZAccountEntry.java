package spring.cloud.dsp.dataaccess.dataobject;

import java.io.Serializable;
import java.util.Date;

public class ZAccountEntry implements Serializable {
    private Integer id;

    private String advertiserId;

    private String action;

    private Integer receivalbleCapital;

    private Integer paidinCapital;

    private Integer kickbackCapital;

    private Integer donationCapital;

    private Float donationRatio;

    private String comments;

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

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId == null ? null : advertiserId.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Integer getReceivalbleCapital() {
        return receivalbleCapital;
    }

    public void setReceivalbleCapital(Integer receivalbleCapital) {
        this.receivalbleCapital = receivalbleCapital;
    }

    public Integer getPaidinCapital() {
        return paidinCapital;
    }

    public void setPaidinCapital(Integer paidinCapital) {
        this.paidinCapital = paidinCapital;
    }

    public Integer getKickbackCapital() {
        return kickbackCapital;
    }

    public void setKickbackCapital(Integer kickbackCapital) {
        this.kickbackCapital = kickbackCapital;
    }

    public Integer getDonationCapital() {
        return donationCapital;
    }

    public void setDonationCapital(Integer donationCapital) {
        this.donationCapital = donationCapital;
    }

    public Float getDonationRatio() {
        return donationRatio;
    }

    public void setDonationRatio(Float donationRatio) {
        this.donationRatio = donationRatio;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
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