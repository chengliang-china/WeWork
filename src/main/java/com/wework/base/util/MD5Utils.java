package com.wework.base.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String md5(String ... args){

        StringBuffer sb = new StringBuffer();
        for(String str : args){
            sb.append(str);
        }

        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }
}
