package com.wework.base.domain.po;

import java.io.Serializable;
import java.util.Date;

public class CouponPO implements Serializable {

    private long couponId ;
    private String couponName ;
    private long couponRuleId;
    private Date endDate;
    private long couponStatus;
    private Date CreateTime;
    private Date startDate;
    private int isDel;

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public long getCouponRuleId() {
        return couponRuleId;
    }

    public void setCouponRuleId(long couponRuleId) {
        this.couponRuleId = couponRuleId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(long couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "CouponPO{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", couponRuleId=" + couponRuleId +
                ", endDate=" + endDate +
                ", couponStatus=" + couponStatus +
                ", CreateTime=" + CreateTime +
                ", startDate=" + startDate +
                '}';
    }
}