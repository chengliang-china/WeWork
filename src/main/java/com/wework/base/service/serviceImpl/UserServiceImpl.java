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
import com.wework.base.util.InvitationCodeUtils;
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

    private static final int CODE_SIZE = 6; // 邀请码长度

    private static final Long USER_SELF = 0L; //个人
    private static final Long USER_COMPANY = 0L; //个人

    @Override
    public UserPO addUserInfo(String token,UserVO userVO) throws Exception{
        int id = 0;
        UserPO userPO = new UserPO();
        try{
            System.out.print("userVO:"+userVO);
            PropertyUtils.copyProperties(userPO,userVO);

            System.out.print("userPo:"+userPO);

            // TODO  不够完善 应该校验一下用户id   接着校验更新是否成功

            id = userMapper.updateUserInfo(userPO);

            // 更新redis
            redisService.remove(token); // 删除redis
            redisService.set(token,userPO,30000L); // 更新redis

        }catch (Exception e){
            e.printStackTrace();
        }

        return userPO;
    }

    @Override
    public UserPO addUserInfoNew(String token,UserVO userVO) throws Exception{
        int id = 0;
        UserPO userPO = new UserPO();
        try{
            System.out.print("userVO:"+userVO);
            PropertyUtils.copyProperties(userPO,userVO);

            System.out.print("userPo:"+userPO);

            // TODO  不够完善 应该校验一下用户id   接着校验更新是否成功

            id = userMapper.updateUserInfoNew(userPO);

            List<UserPO> userById = userMapper.findUserByUserId(userPO);

            if(userById.size() != 1){
                throw new Exception("系统异常");
            }

            userPO = userById.get(0);
            // 更新redis
            redisService.remove(token); // 删除redis
            redisService.set(token,userPO,30000L); // 更新redis

        }catch (Exception e){
            e.printStackTrace();
        }

        return userPO;
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

            // 设置用户类型与parentId  初始用户类型为个人用户 0  parentId 为0   CL
            userPO.setParentId(0L);
            userPO.setUserType(USER_SELF);

            userPO.setOpenid(openid);
            userPO.setAppId(appid);
            userPO.setCreateTime(new Date());
            userPO.setIsDel(0L);

            List<UserPO> userList = userMapper.findUserById(userPO);
            System.out.println("userList:"+userList.size());
            if(userList.size() - 0 == 0){
                // 新建
                userMapper.saveUserInfo(userPO);

            }else if(userList.size() - 1 == 0){
                // 更新
            }else {
                throw new Exception("数据异常，请联系管理员");
            }

            redisService.set(token,userList.get(0),30000L);

            jsonObject.put("userInfo",userList.get(0));
            jsonObject.put("token",token);

        return jsonObject;
    }

    @Override
    public BaseJSON getUserInfo(String token) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            baseJSON.setResult(userPO);
        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }

        return baseJSON;
    }

    @Override
    public BaseJSON exitsInvitationCode(String token) {
        BaseJSON baseJSON =  new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            System.out.println("getOpenid:"+userPO.getOpenid());
            System.out.println("getUserId:"+userPO.getUserId());

            List<UserPO> userList = userMapper.getUserInfoById(userPO.getUserId());

            if(userList.size() - 0 == 0){
                baseJSON.setFail("用户不存在，请重新登陆");
                baseJSON.setCode(100); // 100 表示用户不存在
                return baseJSON;
            }else if(userList.size() - 1 == 0){
                UserPO user = userList.get(0);
                //  查看user的 parent 是否存在
                if((user.getParentId() == null ? 0 : user.getParentId()) - 0 == 0){
                    baseJSON.setCode(2);
                    baseJSON.setMessage("邀请码不存在");
                    return baseJSON;
                }

                List<UserPO> parentList = userMapper.getUserInfoById(user.getParentId());

                if(parentList.size() - 0 == 0){
                    baseJSON.setFail("邀请码失效");
                    return  baseJSON;
                }

                baseJSON.setResult(parentList.get(0));

                return baseJSON;
            }

            baseJSON.setFail("系统异常，请稍后再试");

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }

        return baseJSON;
    }

    @Override
    public BaseJSON createInvitationCode(String token) {
        BaseJSON baseJSON =  new BaseJSON();
        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            List<UserPO> userList = userMapper.getUserInfoById(userPO.getUserId());

            if(userList.size() - 0 == 0){
                baseJSON.setFail("用户不存在，请重新登陆");
                baseJSON.setCode(100); // 100 表示用户不存在
                return baseJSON;
            }else if(userList.size() - 1 == 0){
                UserPO user = userList.get(0);

                System.out.println("验证码："+user.getInvitationCode().equals(""));
                System.out.println("验证码："+user.getInvitationCode().length());
                if(user.getInvitationCode() != null && !user.getInvitationCode().equals("")){
                    baseJSON.setFail("验证码已存在");
                    baseJSON.setCode(3); // 100 表示用户不存在
                    baseJSON.setResult(user.getInvitationCode());
                    return baseJSON;
                }

                user.setInvitationCode(this.getCode(CODE_SIZE));
                user.setUserType(USER_COMPANY);
                userMapper.updateUserInfo(user);

                redisService.remove(token); // 删除redis
                redisService.set(token,userList.get(0),30000L); // 更新redis

                baseJSON.setResult(user.getInvitationCode());

                return baseJSON;
            }

            baseJSON.setFail("系统异常，请稍后再试");
        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }

        return baseJSON;
    }

    @Override
    public BaseJSON fillInInvitationCode(String token, String invitationCode) {

        BaseJSON baseJSON =  new BaseJSON();
        try{
            List<UserPO> parentList = userMapper.existInvitationCode(invitationCode); // 通过验证码获取公司信息

            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            List<UserPO> userList = userMapper.getUserInfoById(userPO.getUserId());

            if(userList.size() - 0 == 0){
                baseJSON.setFail("用户不存在，请重新登陆");
                baseJSON.setCode(100); // 100 表示用户不存在
                return baseJSON;
            }else if(userList.size() - 1 == 0){
                UserPO user = userList.get(0);

                UserPO parentPO = parentList.get(0);

                user.setParentId(parentPO.getUserId());

                System.out.println("parentPo："+parentPO.getUserId());
                System.out.println("user："+user);

                userMapper.updateUserInfo(user);

                redisService.remove(token); // 删除redis
                redisService.set(token,userList.get(0),30000L); // 更新redis

                baseJSON.setResult(user);

                return baseJSON;
            }

            baseJSON.setFail("系统异常，请稍后再试");
        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }



        return baseJSON;

    }

    @Override
    public BaseJSON unbind(String token) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }
            userPO.setParentId(null);

            userMapper.updateUserInfo(userPO);

            redisService.remove(token); // 删除redis
            redisService.set(token,userPO,30000L); // 更新redis

            baseJSON.setResult(userPO);

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试");
        }


        return baseJSON;
    }

    private String createToken(String openid,String session_key){
        return MD5Utils.md5(openid,session_key);
    }

    private String getCode(int size){
        String code  = InvitationCodeUtils.getCode(size);
        List<UserPO> userPOList = userMapper.existInvitationCode(code);

        if(userPOList == null && userPOList.size() > 0){
            this.getCode(size);
        }
        return code;
    }
}
