package com.wework.base.domain.vo;

import com.wework.base.config.BaseCode;

import java.io.Serializable;
import java.util.Date;

public class UserCouponVO implements Serializable {

    private long id;
    private long userId;
    private Long couponId;
    private long status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserCouponVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", status=" + status +
                '}';
    }
}
