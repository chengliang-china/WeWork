package com.wework.base.service;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.CouponRuleVO;

/**
 * 优惠卷规则 服务
 */
public interface CouponRuleService {

    /**
     * 保存优惠卷规则
     */
    public BaseJSON addCouponRule(String token, CouponRuleVO couponRuleVO);

    /**
     * 获取所有规则
     * @return
     */
    BaseJSON getAllCouponRule();
}
