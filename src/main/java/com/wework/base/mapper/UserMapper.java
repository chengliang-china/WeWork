package com.wework.base.mapper;

import com.wework.base.domain.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository(value="UserMapper")
public interface UserMapper {

    /**
     *
     *
     * @Title: insertSysRole2
     *
     * @Description: insertSysRole2 返回自增主键的情况
     *
     * @param userPO
     * @return
     *
     * @return: int
     */
    @Insert({ "insert into user(parent_id, user_name, user_type, id_number,sex,passport_no,region,birthday,blod,education,hobby,invitation_code,app_id,app_secret,phone,wallet_balance,create_time,is_del) " +
            "values(#{parentId}, #{userName}, #{userType}, #{idNumber}, #{sex}, #{passportNo}, #{region}, #{birthday}, #{blod}, #{education}, #{hobby}, #{invitationCode}," +
            " #{appId}, #{appSecret}, #{phone},#{walletBalance},#{createTime},#{isDel})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveUserInfo(UserPO userPO);
}
