package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.CouponRulePO;
import com.wework.base.domain.vo.CouponRuleVO;
import com.wework.base.mapper.CouponRuleMapper;
import com.wework.base.service.CouponRuleService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponRuleServiceImpl implements CouponRuleService {


    @Autowired
    private CouponRuleMapper couponRuleMapper;

    @Override
    public BaseJSON addCouponRule(String token, CouponRuleVO couponRuleVO) {
        BaseJSON baseJSON = new BaseJSON();

        try {
            CouponRulePO couponRulePO = new CouponRulePO();
            PropertyUtils.copyProperties(couponRulePO, couponRuleVO);

            couponRulePO.setCreateTime(new Date());
            couponRulePO.setIsDel(BaseCode.UNDEL);

            couponRuleMapper.addCouponRule(couponRulePO);

        } catch (Exception e) {
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }


        return baseJSON;
    }

    @Override
    public BaseJSON getAllCouponRule() {
        BaseJSON baseJSON = new BaseJSON();
        try {
            List<CouponRulePO> allCouponRule = couponRuleMapper.getAllCouponRule();
            System.out.println("allCouponRule:"+allCouponRule);
            baseJSON.setResult(allCouponRule);
        } catch (Exception e) {
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }
        return baseJSON;
    }
}
