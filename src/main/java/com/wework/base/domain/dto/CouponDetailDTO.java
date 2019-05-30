package com.wework.base.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class CouponDetailDTO implements Serializable {

    private long couponId;
    private String couponName ;
    private Date startDate;
    private Date endDate;
    private String couponRuleName;
    private String description;
    private long satisfy;
    private long less;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCouponRuleName() {
        return couponRuleName;
    }

    public void setCouponRuleName(String couponRuleName) {
        this.couponRuleName = couponRuleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSatisfy() {
        return satisfy;
    }

    public void setSatisfy(long satisfy) {
        this.satisfy = satisfy;
    }

    public long getLess() {
        return less;
    }

    public void setLess(long less) {
        this.less = less;
    }

    @Override
    public String toString() {
        return "CouponDetailDTO{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", couponRuleName='" + couponRuleName + '\'' +
                ", description='" + description + '\'' +
                ", satisfy=" + satisfy +
                ", less=" + less +
                '}';
    }
}
