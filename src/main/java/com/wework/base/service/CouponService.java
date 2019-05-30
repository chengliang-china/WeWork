package com.wework.base.service;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.CouponRuleVO;
import com.wework.base.domain.vo.CouponVO;

public interface CouponService {

    /**
     * 保存优惠卷
     */
    public BaseJSON addCoupon(String token, CouponVO couponVO);

    /**
     * 获取所有优惠卷
     * @return
     */
    BaseJSON getAllCoupon();

    /**
     *
     * @param token
     * @return
     */
    BaseJSON findCouponAvailables(String token);

    /**
     * 领取优惠卷
     * @param token
     * @param couponId
     * @return
     */
    BaseJSON receiveCoupon(String token, long couponId);
}
