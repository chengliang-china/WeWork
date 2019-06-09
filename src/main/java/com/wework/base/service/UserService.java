package com.wework.base.service;

import com.alibaba.fastjson.JSONObject;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;

public interface UserService {

    /**
     * @param userVO 用户信息
     * @return 主键
     */
    UserPO addUserInfo (String token, UserVO userVO) throws Exception;

    /**
     *
     * @param code
     * @return
     */
    JSONObject getOpenId(String code, UserInfoVO userInfoVO) throws Exception;

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    BaseJSON getUserInfo(String token) ;

    /**
     * 判断当前用户是否存在邀请码，如果存在返回父类信息，不存在返回code =2
     *
     * @param token
     * @return
     */
    BaseJSON exitsInvitationCode(String token) ;


    /**
     * 生成邀请码
     * @param token
     * @return
     */
    BaseJSON createInvitationCode(String token);

    /**
     *  为用户添加邀请码
     * @param token
     * @param invitationCode
     * @return
     */
    BaseJSON fillInInvitationCode(String token, String invitationCode);

    /**
     * 解除与公司的绑定
     * @param token
     * @return
     */
    BaseJSON unbind(String token);

    UserPO addUserInfoNew(String token, UserVO userVO)throws Exception;
}
