package com.wework.base.domain.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="couponrule", description="用户对象")
public class CouponRuleVO implements Serializable {

    private String couponRuleName;
    private String description;
    private long satisfy;
    private long less;

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
        return "CouponRuleVO{" +
                "couponRuleName='" + couponRuleName + '\'' +
                ", description='" + description + '\'' +
                ", satisfy=" + satisfy +
                ", less=" + less +
                '}';
    }
}
