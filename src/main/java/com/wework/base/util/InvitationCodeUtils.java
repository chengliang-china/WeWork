package com.wework.base.util;

import com.wework.base.domain.po.UserPO;
import com.wework.base.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class InvitationCodeUtils implements Serializable {

    @Autowired
    private static UserMapper userMapper;

    private static final char[] allChar = new char[]{'D', 'H', 'K', 'A', '4', 'S', 'G', '8', 'B', 'J', 'C', 'L', 'X', '2', 'P', 'E', '6', '5', '7', '9', 'M', '3', 'Y', 'R', 'F', 'T', 'U', 'V', 'W', 'N', 'Q', 'Z'};

    public static String getCode(int codeSize){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<codeSize; i++){
            sb.append(allChar[random.nextInt(31)]);
        }



        boolean b = userMapper == null;
        System.out.println("sb:"+b);
//        List<UserPO> userPOList = userMapper.existInvitationCode(sb.toString());

//        if(userPOList == null && userPOList.size() > 0){
//            getCode(codeSize);
//        }
        return sb.toString().toUpperCase();
    }

}
