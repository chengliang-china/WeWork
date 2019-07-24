package com.wework.base.controller;


import com.wework.base.config.BaseCode;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.ActivePO;
import com.wework.base.domain.po.MemberPO;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.ActiveUserVO;
import com.wework.base.domain.vo.ActiveVO;
import com.wework.base.domain.vo.UserInfoVO;
import com.wework.base.domain.vo.UserVO;
import com.wework.base.service.ActiveService;
import com.wework.base.service.ImageService;
import com.wework.base.service.UserService;
import com.wework.base.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/active")
@Api(tags = { "活动模块" })
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @Autowired
    private ImageService imageService;
    @ApiOperation("用户添加活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "activeId", dataType = "long", required = true, value = "活动", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "姓名", defaultValue = "小明"),
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "电话", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "wechatNo", dataType = "String", required = false, value = "微信号", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "mail", dataType = "String", required = false, value = "邮箱", defaultValue = "123@163.com"),
            @ApiImplicitParam(paramType = "query", name = "company", dataType = "String", required = false, value = "公司名称", defaultValue = "苹果"),
            @ApiImplicitParam(paramType = "query", name = "position", dataType = "String", required = false, value = "职位", defaultValue = "ceo")})
    @RequestMapping(value = "/saveActiveInfo", method = RequestMethod.POST)
    public BaseJSON saveActiveInfo(String token, long userId,long activeId, String name,String phone,String wechatNo,String mail,String company,String position) {

        BaseJSON baseJSON = new BaseJSON();
        ActiveUserVO activeUserVo =new ActiveUserVO();
        activeUserVo.setUserId(userId);
        activeUserVo.setActiveId(activeId);
        activeUserVo.setName(name);
        activeUserVo.setPhone(phone);
        activeUserVo.setWechatNo(wechatNo);
        activeUserVo.setMail(mail);
        activeUserVo.setCompany(company);
        activeUserVo.setPosition(position);
        int a = activeService.saveActiveInfo(activeUserVo);
        if(a<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("新增活动用户失败，请联系管理员！");
        }
        return baseJSON;
    }

/*    @ApiOperation("查询活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/findActiveList", method = RequestMethod.POST)
    public BaseJSON findActiveList(String token) {

        BaseJSON baseJSON = new BaseJSON();
        List<ActiveVO> list = activeService.findActiveList();
        baseJSON.setResult(list);
        return baseJSON;
    }*/

    @ApiOperation("用户添加会员信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "姓名", defaultValue = "小明"),
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "电话", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "wechatNo", dataType = "String", required = true, value = "微信号", defaultValue = "18876543210"),
            @ApiImplicitParam(paramType = "query", name = "mail", dataType = "String", required = true, value = "邮箱", defaultValue = "123@163.com"),
            @ApiImplicitParam(paramType = "query", name = "company", dataType = "String", required = true, value = "公司名称", defaultValue = "苹果"),
            @ApiImplicitParam(paramType = "query", name = "position", dataType = "String", required = true, value = "职位", defaultValue = "ceo")})
    @RequestMapping(value = "/saveMemberInfo", method = RequestMethod.POST)
    public BaseJSON saveMemberInfo(String token, long userId, String name,String phone,String wechatNo,String mail,String company,String position) {

        BaseJSON baseJSON = new BaseJSON();
        MemberPO memberPO =new MemberPO();
        memberPO.setUserId(userId);
        memberPO.setName(name);
        memberPO.setPhone(phone);
        memberPO.setWechatNo(wechatNo);
        memberPO.setMail(mail);
        memberPO.setCompany(company);
        memberPO.setPosition(position);
        activeService.saveMemberInfo(memberPO);
        return baseJSON;
    }

    @ApiOperation("查询会员信息")
    @RequestMapping(value = "/findMemberList", method = RequestMethod.GET)
    public BaseJSON findMemberList() {

        BaseJSON baseJSON = new BaseJSON();
        List<MemberPO> list = activeService.findMemberList();
        baseJSON.setResult(list);
        return baseJSON;
    }
    @ApiOperation("根据活动状态查询活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "activeStatus", dataType = "int", required = true, value = "活动状态", defaultValue = "10013001")})
    @RequestMapping(value = "/findActiveByStatus", method = RequestMethod.POST)
    public BaseJSON findActiveByStatus(long userId,int activeStatus) {

        BaseJSON baseJSON = new BaseJSON();
        List<ActiveVO> list = activeService.findActiveByStatus(userId,activeStatus);
        baseJSON.setResult(list);
        return baseJSON;
    }

    @ApiOperation("增加活动信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "activeName", dataType = "String", required = true, value = "活动名", defaultValue = "新春大酬宾"),
            @ApiImplicitParam(paramType = "query", name = "activeAddr", dataType = "String", required = true, value = "活动地址", defaultValue = "北京天安门广场"),
            @ApiImplicitParam(paramType = "query", name = "activeTime", dataType = "String", required = true, value = "活动时间", defaultValue = "2019-08-23 16:45:12"),
            @ApiImplicitParam(paramType = "query", name = "activeIntroduction", dataType = "String", required = false, value = "活动介绍", defaultValue = "123@163.com")})
    @ResponseBody
    @RequestMapping(value = "/saveActive", method = RequestMethod.POST)
    public BaseJSON saveActive(String activeName, String activeAddr, String activeTime, String activeIntroduction, HttpServletRequest request, MultipartFile file) {

        BaseJSON baseJSON = new BaseJSON();
        ActivePO activePo =new ActivePO();
        activePo.setActiveName(activeName);
        activePo.setActiveAddr(activeAddr);
        activePo.setActiveStatus(BaseCode.PROCESSING);
        activePo.setActiveIntroduction(activeIntroduction);
        try {
            activePo.setActiveTime(DateUtils.stringToDateTime(activeTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //图片上传
        if (null != file) {
            try {

                String head = imageService.uploadImage(file);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
                activePo.setActiveUrl(head);
            } catch (Exception e) {
                baseJSON.setCode(1);
                baseJSON.setResult("失败");
                baseJSON.setMessage("图片上传失败，请联系管理员！");
            }
        }
        int a = activeService.saveActive(activePo);
        if(a<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("新增活动用户失败，请联系管理员！");
        }
        return baseJSON;
    }
}