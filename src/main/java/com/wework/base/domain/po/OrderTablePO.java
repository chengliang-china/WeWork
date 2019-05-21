package com.wework.base.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTablePO {
    private long orderId;
    private long storeId;
    private long userId;
    private Date useStartTime;
    private Date useEndTime;
    private int  useHours;
    private BigDecimal applyFee;
    private int orderStatus;
    private long couponId;
    private Date createTime;
    private long isDel;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public int getUseHours() {
        return useHours;
    }

    public void setUseHours(int useHours) {
        this.useHours = useHours;
    }

    public BigDecimal getApplyFee() {
        return applyFee;
    }

    public void setApplyFee(BigDecimal applyFee) {
        this.applyFee = applyFee;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getIsDel() {
        return isDel;
    }

    public void setIsDel(long isDel) {
        this.isDel = isDel;
    }
}
