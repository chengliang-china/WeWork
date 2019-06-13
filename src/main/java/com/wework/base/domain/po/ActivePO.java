package com.wework.base.domain.po;

import java.util.Date;

public class ActivePO {
    private long id;
    private String activeName;
    private int activeStatus;
    private String activeAddr;
    private Date activeTime;
    private String activeIntroduction;
    private String activeUrl;

    public String getActiveUrl() {
        return activeUrl;
    }

    public void setActiveUrl(String activeUrl) {
        this.activeUrl = activeUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getActiveAddr() {
        return activeAddr;
    }

    public void setActiveAddr(String activeAddr) {
        this.activeAddr = activeAddr;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveIntroduction() {
        return activeIntroduction;
    }

    public void setActiveIntroduction(String activeIntroduction) {
        this.activeIntroduction = activeIntroduction;
    }

}
