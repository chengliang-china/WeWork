package com.wework.base.mapper;


import com.wework.base.domain.po.UserCouponPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value="UserCouponMapper")
public interface UserCouponMapper {

    @Select("select * from user_coupon where user_id = #{userId}")
    List<UserCouponPO> findUserCouponByUserId(@Param("userId") long userId);

}
