package com.wework.base.service;

import com.wework.base.domain.vo.UserVO;

public interface UserService {

    /**
     * @param userVO 用户信息
     * @return 主键
     */
    int addUserInfo (UserVO userVO) throws Exception;
}
