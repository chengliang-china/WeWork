package com.wework.base.mapper;

import com.wework.base.domain.po.CouponRulePO;
import com.wework.base.domain.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value="CouponRuleMapper")
public interface CouponRuleMapper {

    @Insert({ "insert into coupon_rule(coupon_rule_name,description,create_time,is_del,satisfy,less)" +
            " values(#{couponRuleName},#{description},#{CreateTime},#{isDel},#{satisfy},#{less})" })
    @Options(useGeneratedKeys = true, keyProperty = "coupon_rule_id")
    int addCouponRule(CouponRulePO couponRulePO);

    @Select({ "select * from coupon_rule where is_del = 0" })
    List<CouponRulePO> getAllCouponRule();
}
