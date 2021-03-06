package com.wework.base.domain.vo;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class StoreVO {
    private long storeId;
    private String storeName;
    private BigDecimal applyFee;
    private String storeIntroduction;
    private String arrivalWay;
    private Time offStartTime;
    private Time offEndTime;
    private Time openStartTime;
    private Time openEndTime;
    private long couponId;
    private long createTime;
    private long isDel;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String thumbnailUrl;
    private int seatIsEnough;

    public int getSeatIsEnough() {
        return seatIsEnough;
    }

    public void setSeatIsEnough(int seatIsEnough) {
        this.seatIsEnough = seatIsEnough;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public BigDecimal getApplyFee() {
        return applyFee;
    }

    public void setApplyFee(BigDecimal applyFee) {
        this.applyFee = applyFee;
    }

    public String getStoreIntroduction() {
        return storeIntroduction;
    }

    public void setStoreIntroduction(String storeIntroduction) {
        this.storeIntroduction = storeIntroduction;
    }

    public String getArrivalWay() {
        return arrivalWay;
    }

    public void setArrivalWay(String arrivalWay) {
        this.arrivalWay = arrivalWay;
    }

    public Date getOpenStartTime() {
        return openStartTime;
    }

    public void setOpenStartTime(Time openStartTime) {
        this.openStartTime = openStartTime;
    }

    public Time getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Time openEndTime) {
        this.openEndTime = openEndTime;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getIsDel() {
        return isDel;
    }

    public void setIsDel(long isDel) {
        this.isDel = isDel;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Time getOffStartTime() {
        return offStartTime;
    }

    public void setOffStartTime(Time offStartTime) {
        this.offStartTime = offStartTime;
    }

    public Time getOffEndTime() {
        return offEndTime;
    }

    public void setOffEndTime(Time offEndTime) {
        this.offEndTime = offEndTime;
    }
}
