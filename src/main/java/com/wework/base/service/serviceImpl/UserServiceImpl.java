package com.wework.base.service.serviceImpl;

import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.UserVO;
import com.wework.base.mapper.UserMapper;
import com.wework.base.service.UserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public int addUserInfo(UserVO userVO) throws Exception{

        UserPO userPO = new UserPO();
        PropertyUtils.copyProperties(userPO,userVO);
        
        System.out.print("userPo:"+userPO);
        int id = userMapper.saveUserInfo(userPO);


        return 110;
    }
}
