package com.wework.base.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;
import com.wework.base.mapper.UserMapper;
import com.wework.base.service.RedisService;
import com.wework.base.service.UserService;
import com.wework.base.util.HttpUtils;
import com.wework.base.util.MD5Utils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Value("${weixin.login.url}")
    private volatile String requestUrl ;

    @Value("${weixin.appid}")
    private volatile String appid ;

    @Value("${weixin.appSecret}")
    private volatile String appSecret ;

    
    @Override
    public int addUserInfo(UserVO userVO) throws Exception{

        UserPO userPO = new UserPO();
        PropertyUtils.copyProperties(userPO,userVO);
        
        System.out.print("userPo:"+userPO);
        int id = userMapper.saveUserInfo(userPO);
        return id;
    }

    @Override
        public JSONObject getOpenId(String code, UserInfoVO userInfoVO) throws Exception{

            Map<String,String> requestUrlParam = new HashMap<String,String>();
            requestUrlParam.put("appid", appid);	//开发者设置中的appId
            requestUrlParam.put("secret", appSecret);	//开发者设置中的appSecret
            requestUrlParam.put("js_code", code);	//小程序调用wx.login返回的code
            requestUrlParam.put("grant_type", "authorization_code");	//默认参数

            //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
            JSONObject jsonObject = JSON.parseObject(HttpUtils.sendPost(requestUrl, requestUrlParam));

            if(jsonObject.containsKey("errcode")){
                return jsonObject;
            }

            String openid = jsonObject.getString("openid");
            String session_key = jsonObject.getString("session_key");

            // 生成token
            String token = createToken(openid, session_key);

            // 请求成功  整理用户数据
            UserPO userPO = new UserPO();
            BeanUtils.copyProperties(userInfoVO,userPO);

            userPO.setOpenid(openid);
            userPO.setAppId(appid);
            userPO.setCreateTime(new Date());
            userPO.setIsDel(0L);


            List<UserPO> userList = userMapper.findUserById(userPO);

            if(userList.size() - 0 == 0){
                // 新建
                userMapper.saveUserInfo(userPO);

            }else if(userList.size() - 1 == 0){
                // 更新
            }else {
                throw new Exception("数据异常，请联系管理员");
            }

            redisService.set(token,userPO,30000L);
//            System.out.println("openid:"+openid);
//            System.out.println("session_key:"+session_key);
//            System.out.println("userVO:"+userPO);
            jsonObject.put("userInfo",userPO);
            jsonObject.put("token",token);

        return jsonObject;
    }

    @Override
    public BaseJSON getUserInfo(String token) {
        BaseJSON baseJSON = new BaseJSON();
        UserPO userPO = (UserPO) redisService.get(token);

        if(userPO == null){
            baseJSON.setFail("token 过期 请重新登陆！");
            return baseJSON;
        }

        baseJSON.setResult(userPO);
        return baseJSON;
    }

    private String createToken(String openid,String session_key){
        return MD5Utils.md5(openid,session_key);
    }
}
