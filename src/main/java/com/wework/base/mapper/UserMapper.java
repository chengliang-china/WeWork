package com.wework.base.mapper;

import com.wework.base.domain.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Insert({ "insert into user(parent_id, user_name, user_type, id_number,gender,passport_no,region,birthday,blod,education,hobby,invitation_code,app_id," +
            "phone,wallet_balance,create_time,is_del,province,city,country,nick_name,avatar_url,openid) " +
            "values(#{parentId}, #{userName}, #{userType}, #{idNumber}, #{gender}, #{passportNo}, #{region}, #{birthday}, #{blod}, #{education}, #{hobby}, #{invitationCode}," +
            " #{appId},  #{phone},#{walletBalance},#{createTime},#{isDel},#{province},#{city},#{country},#{nickName},#{avatarUrl},#{openid})" })
    @Options(useGeneratedKeys = true, keyProperty = "user_id")
    int saveUserInfo(UserPO userPO);

    @Select("select * from user where openid = #{openId}")
    public List<UserPO> findUserById(UserPO userPO);
}
