package com.wework.base.domain.po;

import com.wework.base.config.BaseCode;

import java.io.Serializable;
import java.util.Date;

public class UserCouponPO implements Serializable {

    private long id;
    private long userId;
    private Long couponId;
    private long status = BaseCode.TO_BE_USED;
    private Date createTime = new Date();
    private int isDel = BaseCode.UNDEL;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "UserCouponPO{" +
                "id=" + id +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", isDel=" + isDel +
                '}';
    }
}
