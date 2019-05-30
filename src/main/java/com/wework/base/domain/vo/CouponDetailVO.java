package com.wework.base.domain.vo;

import com.wework.base.domain.dto.CouponDetailDTO;

import java.io.Serializable;
import java.util.List;

public class CouponDetailVO implements Serializable {

    List<CouponDetailDTO> availableList;

    List<CouponDetailDTO> notAvailableList;

    public List<CouponDetailDTO> getAvailableList() {
        return availableList;
    }

    public void setAvailableList(List<CouponDetailDTO> availableList) {
        this.availableList = availableList;
    }

    public List<CouponDetailDTO> getNotAvailableList() {
        return notAvailableList;
    }

    public void setNotAvailableList(List<CouponDetailDTO> notAvailableList) {
        this.notAvailableList = notAvailableList;
    }

    @Override
    public String toString() {
        return "CouponDetailVO{" +
                "availableList=" + availableList +
                ", notAvailableList=" + notAvailableList +
                '}';
    }
}
