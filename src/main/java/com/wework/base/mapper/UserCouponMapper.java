package com.wework.base.mapper;


import com.wework.base.config.BaseCode;
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

}
