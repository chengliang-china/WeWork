package com.wework.base.controller;

import com.wework.base.domain.base.BaseJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/user")
@Api(tags = { "用户模块" })
public class UserController {
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account", dataType = "String", required = true, value = "用户名", defaultValue = "chengliang"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "密码", defaultValue = "123456") })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public BaseJSON getTableList(String account, String password) {
        BaseJSON baseJSON = new BaseJSON();
        baseJSON.setResult(account + "-" + password);
        return baseJSON;
    }
}
