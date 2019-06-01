package com.wework.base.mapper;


import com.wework.base.config.BaseCode;
import com.wework.base.domain.dto.UserCouponDetailDTO;
import com.wework.base.domain.po.UserCouponPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value="UserCouponMapper")
public interface UserCouponMapper {

    @Select("select * from user_coupon where user_id = #{userId} and is_del = "+ BaseCode.UNDEL)
    List<UserCouponPO> findUserCouponByUserId(@Param("userId") long userId);

    @Select("select * from user_coupon where user_id = #{userId} and coupon_id = #{couponId} and is_del = "+ BaseCode.UNDEL)
    List<UserCouponPO> receivedCoupon(@Param("userId") long userId,@Param("couponId") long couponId);

    @Insert("insert into user_coupon(user_id,coupon_id,create_time,is_del,status) values(#{userId},#{couponId},#{createTime},#{isDel},#{status})")
    int receiveCoupon (UserCouponPO userCouponPO);

    @Select("select c.coupon_id ,uc.status,c.coupon_name,cr.description,c.coupon_status,c.end_date,cr.satisfy,cr.less from user_coupon uc " +
            "left join coupon c on uc.coupon_id = c.coupon_id" +
            " left join coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id" +
            " where uc.user_id = #{userId} and uc.status = "+BaseCode.TO_BE_USED+" and c.coupon_status = "+BaseCode.VALID+" and uc.is_del = 0 and c.is_del = 0 and cr.is_del = 0 order by uc.create_time desc")
    List<UserCouponDetailDTO> getUserCouponListForUnUsed(@Param("userId") Long userId);

    @Select("select c.coupon_id ,uc.status,c.coupon_name,cr.description,c.coupon_status,c.end_date,cr.satisfy,cr.less from user_coupon uc " +
            "left join coupon c on uc.coupon_id = c.coupon_id" +
            " left join coupon_rule cr on c.coupon_rule_id = cr.coupon_rule_id" +
            " where uc.user_id = #{userId} and uc.status = "+BaseCode.USED+" or c.coupon_status = "+BaseCode.INVALID+" and uc.is_del = 0 and c.is_del = 0 and cr.is_del = 0 order by uc.create_time desc")
    List<UserCouponDetailDTO> getUserCouponListForUsed(@Param("userId") Long userId);
}
