package com.wework.base.mapper;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.dto.CouponDetailDTO;
import com.wework.base.domain.po.CouponPO;
import com.wework.base.domain.po.CouponRulePO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value="CouponMapper")
public interface CouponMapper {

    @Insert({ "insert into coupon(coupon_name,coupon_rule_id,end_date,coupon_status,create_time,is_del,start_date)" +
            " values(#{couponName},#{couponRuleId},#{endDate},#{couponStatus},#{CreateTime},#{isDel},#{startDate})" })
    @Options(useGeneratedKeys = true, keyProperty = "coupon_id")
    int addCoupon(CouponPO couponPO);

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id ")
    List<CouponDetailDTO> findCouponDetails();

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id where c.coupon_status = " + BaseCode.VALID)
    List<CouponDetailDTO> findCouponDetails4Valid();

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id where c.coupon_status = " + BaseCode.INVALID)
    List<CouponDetailDTO> findCouponDetails4Invalid();

    @Update("update coupon set coupon_status = "+BaseCode.INVALID+" where end_date < NOW()")
    int updateAll();
}
