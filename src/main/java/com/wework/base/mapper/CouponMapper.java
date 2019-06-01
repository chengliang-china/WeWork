package com.wework.base.mapper;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.dto.CouponDetailDTO;
import com.wework.base.domain.po.CouponPO;
import com.wework.base.domain.po.CouponRulePO;
import com.wework.base.domain.po.UserCouponPO;
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

    @Select("select * from coupon where redemption_code = #{rCode} and is_del = "+ BaseCode.UNDEL)
    List<CouponPO> findUserCouponByRcode(@Param("rCode") String rCode);

    @Select("select * from coupon where coupon_id = #{couponId} and coupon_status = "+BaseCode.VALID+" and is_del = "+ BaseCode.UNDEL)
    List<CouponPO> findUserCouponByUserId(@Param("couponId") long couponId);

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id ")
    List<CouponDetailDTO> findCouponDetails();

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id where c.coupon_status = " + BaseCode.VALID)
    List<CouponDetailDTO> findCouponDetails4Valid();

    @Select("select coupon_id,coupon_name,start_date,end_date,coupon_rule_name,description,satisfy,less FROM coupon c LEFT JOIN coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id where c.coupon_status = " + BaseCode.INVALID)
    List<CouponDetailDTO> findCouponDetails4Invalid();

    @Update("update coupon set coupon_status = "+BaseCode.INVALID+" where end_date < NOW()")
    int updateAll();

    @Update("update coupon set redemption_code = #{redemptionCode} where coupon_id = #{couponId}")
    int updateByCouponId(CouponPO couponPO);

    @Select("select * from coupon where coupon_status = "+BaseCode.VALID+" and is_del = "+BaseCode.UNDEL)
    List<CouponPO> getAllCoupon();
}
