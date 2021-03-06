package com.wework.base.domain.po;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class StorePO {
    private long storeId;
    private String storeName;
    private String city;
    private BigDecimal applyFee;
    private String storeIntroduction;
    private Integer storeType;
    private String arrivalWay;
    private Time openStartTime;
    private Time openEndTime;
    private Time offStartTime;
    private Time offEndTime;
    private long couponId;
    private Date createTime;
    private long isDel;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String thumbnailUrl;
    private  Integer seatNum;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
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

    public Time getOpenStartTime() {
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
