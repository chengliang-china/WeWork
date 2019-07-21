package com.wework.base.domain.vo;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class StoreDetailVO {
    private long storeId;
    private String storeName;
    private BigDecimal applyFee;
    private String storeIntroduction;
    private String arrivalWay;
    private Time offStartTime;
    private Time offEndTime;
    private Date openStartTime;
    private Date openEndTime;
    private long couponId;
    private long createTime;
    private long isDel;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal score;
    private String description;
    private String evaluateName;
    private Date evaluateTime;


    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    private List<String> imageList;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    private List<String> serviceList;

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
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

    public void setOpenStartTime(Date openStartTime) {
        this.openStartTime = openStartTime;
    }

    public Date getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Date openEndTime) {
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
