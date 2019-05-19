package com.wework.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @Value("${weixin.login.url}")
    private volatile String requestUrl ;

    @Value("${weixin.appid}")
    private volatile String appid ;

    @Value("${weixin.appSecret}")
    private volatile String appSecret ;

    @ApiOperation("微信端登录通过code值获取 获取openid用户唯一标识")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", dataType = "String", required = true, value = "小程序调用wx.login返回的code", defaultValue = "chengliang") })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public BaseJSON getTableList(String code) {
        BaseJSON baseJSON = new BaseJSON();

        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", appid);	//开发者设置中的appId
        requestUrlParam.put("secret", appSecret);	//开发者设置中的appSecret
        requestUrlParam.put("js_code", code);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");	//默认参数

        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpUtils.sendPost(requestUrl, requestUrlParam));

        baseJSON.setResult(jsonObject);
        return baseJSON;
    }
}
