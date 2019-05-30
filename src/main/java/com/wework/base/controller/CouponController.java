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
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public BaseJSON addCoupon(@RequestParam String token, @RequestBody CouponVO couponVO) {
        return couponService.addCoupon(token, couponVO);
    }

//    @ApiOperation("获取全部优惠卷信息")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
//    @RequestMapping(value = "/findCouponDetails", method = RequestMethod.POST)
//    public BaseJSON findCouponDetails(@RequestParam String token) {
//        return couponService.findCouponDetails(token);
//    }

    @ApiOperation("获取可领取与不可领取的优惠卷信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/findCouponAvailables", method = RequestMethod.POST)
    public BaseJSON findCouponAvailables(@RequestParam String token) {
        return couponService.findCouponAvailables(token);
    }

}
