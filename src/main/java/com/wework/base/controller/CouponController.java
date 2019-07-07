package com.wework.base.controller;


import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.CouponRuleVO;
import com.wework.base.domain.vo.CouponVO;
import com.wework.base.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/coupon")
@Api(tags = { "优惠卷模块" })
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation("添加优惠卷")
    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public BaseJSON addCoupon( @RequestBody CouponVO couponVO) {
        return couponService.addCoupon(couponVO);
    }

    @ApiOperation("获取所有有效的优惠卷")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/getAllCoupon", method = RequestMethod.POST)
    public BaseJSON getAllCoupon(@RequestParam String token) {
        return couponService.getAllCoupon();
    }

    @ApiOperation("生成兑换码接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "couponId", dataType = "long", required = true, value = "long", defaultValue = "")})
    @RequestMapping(value = "/addRCode", method = RequestMethod.POST)
    public BaseJSON addRCode(@RequestParam String token,@RequestParam long couponId) {
        return couponService.addRCode(couponId);
    }

    @ApiOperation("使用兑换码获取优惠卷接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "rCode", dataType = "String", required = true, value = "rCode", defaultValue = "")})
    @RequestMapping(value = "/getCoupon4RCode", method = RequestMethod.POST)
    public BaseJSON getCoupon4RCode(@RequestParam String token,@RequestParam String rCode) {
        return couponService.getCoupon4RCode(token,rCode);
    }


    @ApiOperation("获取可领取与不可领取的优惠卷信息 页面弹框领取使用")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/findCouponAvailables", method = RequestMethod.POST)
    public BaseJSON findCouponAvailables(@RequestParam String token) {
        return couponService.findCouponAvailables(token);
    }

//    @ApiOperation("领取优惠卷")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
//            @ApiImplicitParam(paramType = "query", name = "couponId", dataType = "long", required = true, value = "long", defaultValue = "")})
//    @RequestMapping(value = "/receiveCoupon", method = RequestMethod.POST)
//    public BaseJSON receiveCoupon(@RequestParam String token,@RequestParam long couponId) {
//        return couponService.receiveCoupon(token,couponId);
//    }

    @ApiOperation("领取所有优惠卷")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/receiveAllCoupon", method = RequestMethod.POST)
    public BaseJSON receiveAllCoupon(@RequestParam String token) {
        return couponService.receiveAllCoupon(token);
    }


    @ApiOperation("获取用户已使用/未使用/已过期优惠卷列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/getUserCouponList", method = RequestMethod.POST)
    public BaseJSON getUserCouponList(@RequestParam String token) {
        return couponService.getUserCouponList(token);
    }


    @ApiOperation("获取未使用优惠卷数量")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/getUnUsedCouponCount", method = RequestMethod.POST)
    public BaseJSON getUnUsedCouponCount(@RequestParam String token) {
        return couponService.getUnUsedCouponCount(token);
    }
}
