package com.wework.base.domain.po;

public class CarouselMapPO {
    private long id;
    private String activeImage;
    private String couponImage;
    private String generalImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(String activeImage) {
        this.activeImage = activeImage;
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage;
    }

    public String getGeneralImage() {
        return generalImage;
    }

    public void setGeneralImage(String generalImage) {
        this.generalImage = generalImage;
    }
}
