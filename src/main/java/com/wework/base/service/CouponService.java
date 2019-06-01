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

    /**
     * 领取所有优惠卷
     * @param token
     * @return
     */
    BaseJSON receiveAllCoupon(String token);

    /**
     * 获取用户已使用/未使用/已过期优惠卷列表
     * @param token
     * @return
     */
    BaseJSON getUserCouponList(String token);

    /**
     * 生成兑换码
     * @return
     */
    BaseJSON addRCode(long couponId);

    /**
     * 使用兑换码获取优惠卷
     * @param token
     * @param rCode
     * @return
     */
    BaseJSON getCoupon4RCode(String token, String rCode);
}
