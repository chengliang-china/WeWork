package com.wework.base.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 优惠卷 已使用 未使用 详情
 */
public class UserCouponDetailDTO implements Serializable {
    private long couponId;
    private long status;
    private String couponName;
    private String description;
    private long couponStatus;
    private Date endDate;
    private long satisfy;
    private long less;

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(long couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        return "UserCouponDetailDTO{" +
                "couponId=" + couponId +
                ", status=" + status +
                ", couponName='" + couponName + '\'' +
                ", description='" + description + '\'' +
                ", couponStatus=" + couponStatus +
                ", endDate=" + endDate +
                ", satisfy=" + satisfy +
                ", less=" + less +
                '}';
    }
}
