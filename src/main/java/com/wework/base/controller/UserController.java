package com.wework.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;
import com.wework.base.service.UserService;
import com.wework.base.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@EnableSwagger2
@RequestMapping("/user")
@Api(tags = { "用户模块" })
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("微信端登录通过code值获取 获取openid用户唯一标识")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", dataType = "String", required = true, value = "小程序调用wx.login返回的code", defaultValue = "071bhjLv07ZRIi1ATRJv0ptmLv0bhjL1"),})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseJSON getOpenId(@RequestParam("code") String code, @RequestBody UserInfoVO userInfoVO) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            baseJSON.setResult(userService.getOpenId(code,userInfoVO));
        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail();
        }


        return baseJSON;
    }

    @ApiOperation("更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),})
    @RequestMapping(value = "/save/userInfo", method = RequestMethod.POST)
    public BaseJSON saveUserInfo(@RequestParam("token") String token ,@RequestBody UserVO userVO) {
        BaseJSON baseJSON = new BaseJSON();
        try{
            UserPO userPO = userService.addUserInfo(token,userVO);
            baseJSON.setResult(userPO);
        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail();
        }
        return baseJSON;
    }

    @ApiOperation("通过token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),})
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public BaseJSON getUserInfo(@Param("token") String token) {
        return userService.getUserInfo(token);
    }

    @ApiOperation("检测用户是否存在邀请码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),})
    @RequestMapping(value = "/exitsInvitationCode", method = RequestMethod.GET)
    public BaseJSON exitsInvitationCode(@Param("token") String token) {
        return userService.exitsInvitationCode(token);
    }

    @ApiOperation("生成邀请码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),})
    @RequestMapping(value = "/createInvitationCode", method = RequestMethod.GET)
    public BaseJSON createInvitationCode(@Param("token") String token) {
        return userService.createInvitationCode(token);
    }

    @ApiOperation("填写邀请码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "InvitationCode", dataType = "String", required = true, value = "邀请码", defaultValue = "")})
    @RequestMapping(value = "/fillInInvitationCode", method = RequestMethod.GET)
    public BaseJSON fillInInvitationCode(@Param("token") String token,@Param("invitationCode") String InvitationCode) {
        return userService.fillInInvitationCode(token,InvitationCode);
    }

    @ApiOperation("解除与公司的绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/fillInInvitationCode", method = RequestMethod.POST)
    public BaseJSON unbind(@Param("token") String token) {
        return userService.unbind(token);
    }
}