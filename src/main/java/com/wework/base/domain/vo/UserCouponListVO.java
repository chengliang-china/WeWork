package com.wework.base.domain.vo;

import com.wework.base.domain.dto.UserCouponDetailDTO;

import java.io.Serializable;
import java.util.List;

public class UserCouponListVO implements Serializable {

    List<UserCouponDetailDTO> unUsed ;
    List<UserCouponDetailDTO> usedAndExpired ;

    public List<UserCouponDetailDTO> getUnUsed() {
        return unUsed;
    }

    public void setUnUsed(List<UserCouponDetailDTO> unUsed) {
        this.unUsed = unUsed;
    }

    public List<UserCouponDetailDTO> getUsedAndExpired() {
        return usedAndExpired;
    }

    public void setUsedAndExpired(List<UserCouponDetailDTO> usedAndExpired) {
        this.usedAndExpired = usedAndExpired;
    }

    @Override
    public String toString() {
        return "UserCouponListVO{" +
                "unUsed=" + unUsed +
                ", usedAndExpired=" + usedAndExpired +
                '}';
    }
}
