package com.wework.base.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ActiveVO {
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

    private int isSignUp;

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

    public int getIsSignUp() {
        return isSignUp;
    }

    public void setIsSignUp(int isSignUp) {
        this.isSignUp = isSignUp;
    }
}
