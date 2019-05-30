package com.wework.base.controller;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.CouponRuleVO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.service.CouponRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/coupon/rule")
@Api(tags = { "优惠卷规则模块" })
public class CouponRuleController {

    @Autowired
    private CouponRuleService couponRuleService;

    @ApiOperation("设置优惠卷规则")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/addCouponRule", method = RequestMethod.POST)
    public BaseJSON addCouponRule(@RequestParam String token, @RequestBody CouponRuleVO couponRuleVO) {
        return couponRuleService.addCouponRule(token,couponRuleVO);
    }

    @ApiOperation("设置优惠卷规则")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/getAllCouponRule", method = RequestMethod.GET)
    public BaseJSON getAllCouponRule(@RequestParam String token) {
        return couponRuleService.getAllCouponRule();
    }
}
