package com.wework.base.domain.po;

import java.io.Serializable;
import java.util.Date;

public class CouponRulePO implements Serializable {

    private long couponRuleId;
    private String couponRuleName;
    private String description;
    private Date CreateTime;
    private int isDel;
    private long satisfy;
    private long less;

    public long getCouponRuleId() {
        return couponRuleId;
    }

    public void setCouponRuleId(long couponRuleId) {
        this.couponRuleId = couponRuleId;
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

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
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
        return "CouponRulePO{" +
                "couponRuleId=" + couponRuleId +
                ", couponRuleName='" + couponRuleName + '\'' +
                ", description='" + description + '\'' +
                ", CreateTime=" + CreateTime +
                ", isDel=" + isDel +
                ", satisfy=" + satisfy +
                ", less=" + less +
                '}';
    }
}
