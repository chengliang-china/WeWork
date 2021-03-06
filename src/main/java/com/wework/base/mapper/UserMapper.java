package com.wework.base.mapper;

import com.wework.base.domain.po.UserPO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
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

    @Select("select * from user where user_id = #{userId}")
    public List<UserPO> findUserByUserId(UserPO userPO);

    @Select("select * from user where user_id = #{userId}")
    public List<UserPO> getUserInfoById(@Param("userId") Long userId);

    @Select("select * from user where invitation_code = #{invitation_code}")
    public List<UserPO> existInvitationCode(@Param("invitation_code") String invitation_code);

    @Update({ "update user set parent_id = #{parentId},user_name = #{userName},user_type = #{userType},id_number = #{idNumber}," +
            "gender = #{gender},passport_no = #{passportNo},region = #{region},birthday = #{birthday}," +
            "blod = #{blod},education = #{education},hobby = #{hobby},invitation_code = #{invitationCode}," +
            "app_id = #{appId},phone = #{phone},wallet_balance = #{walletBalance},create_time = #{createTime}," +
            "is_del = #{isDel},province = #{province},city = #{city},country = #{country}," +
            "nick_name = #{nickName},avatar_url = #{avatarUrl}" +
            " where user_id = #{userId}" })
    int updateUserInfo(UserPO user);

    @Update({ "update user set user_name = #{userName},id_number = #{idNumber}," +
            "gender = #{gender},birthday = #{birthday},education = #{education}"+
            " where user_id = #{userId}" })
    int updateUserInfoNew(UserPO userPO);
}
