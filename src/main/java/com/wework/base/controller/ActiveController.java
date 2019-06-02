package com.wework.base.controller;


import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.ActiveVO;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;
import com.wework.base.service.ActiveService;
import com.wework.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/active")
@Api(tags = { "活动模块" })
public class ActiveController {

    @Autowired
    private ActiveService activeService;
    @ApiOperation("录入活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "姓名", defaultValue = "小明"),
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "电话", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "wechatNo", dataType = "String", required = false, value = "微信号", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "mail", dataType = "String", required = false, value = "邮箱", defaultValue = "123@163.com"),
            @ApiImplicitParam(paramType = "query", name = "company", dataType = "String", required = false, value = "公司名称", defaultValue = "苹果"),
            @ApiImplicitParam(paramType = "query", name = "position", dataType = "String", required = false, value = "职位", defaultValue = "ceo")})
    @RequestMapping(value = "/saveActiveInfo", method = RequestMethod.POST)
    public BaseJSON saveActiveInfo(String token, String name,String phone,String wechatNo,String mail,String company,String position) {

        BaseJSON baseJSON = new BaseJSON();
        ActiveVO activeVO =new ActiveVO();
        activeVO.setName(name);
        activeVO.setPhone(phone);
        activeVO.setWechatNo(wechatNo);
        activeVO.setMail(mail);
        activeVO.setCompany(company);
        activeVO.setPosition(position);
        int a = activeService.saveActiveInfo(activeVO);
        if(a<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("新增活动用户失败，请联系管理员！");
        }
        return baseJSON;
    }

    @ApiOperation("查询活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/findActiveList", method = RequestMethod.POST)
    public BaseJSON findActiveList(String token) {

        BaseJSON baseJSON = new BaseJSON();
        List<ActiveVO> list = activeService.findActiveList();
        baseJSON.setResult(list);
        return baseJSON;
    }
}