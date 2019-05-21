package com.wework.base.service;

import com.alibaba.fastjson.JSONObject;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;

public interface UserService {

    /**
     * @param userVO 用户信息
     * @return 主键
     */
    int addUserInfo (UserVO userVO) throws Exception;

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
}
