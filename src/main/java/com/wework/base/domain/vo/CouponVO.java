package com.wework.base.domain.vo;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="coupon", description="优惠卷对象")
public class CouponVO implements Serializable {

    private String couponName ;
    private long couponRuleId;
    private Date endDate;
    private Date CreateTime;
    private Date startDate;

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
                ", couponName='" + couponName + '\'' +
                ", couponRuleId=" + couponRuleId +
                ", endDate=" + endDate +
                ", CreateTime=" + CreateTime +
                ", startDate=" + startDate +
                '}';
    }
}
