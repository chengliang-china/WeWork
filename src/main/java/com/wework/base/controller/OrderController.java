package com.wework.base.controller;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.OrderVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.service.OrderService;
import com.wework.base.service.StoreService;
import com.wework.base.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/order")
@Api(tags = { "订单模块" })
public class OrderController {

    @Autowired
    private OrderService orderService;
    @ApiOperation("扫码生成订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "long", required = true, value = "门店标识", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户标识", defaultValue = "5")})
    @RequestMapping(value = "/scanCodeCreateOrder", method = RequestMethod.POST)
    public BaseJSON scanCodeCreateOrder(long storeId, long userId) {

        BaseJSON baseJSON = new BaseJSON();
        //检测该用户是否存在未结算订单

        //生成订单
        int i = orderService.createOrder(storeId,userId);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
        }
        return baseJSON;
    }

    @ApiOperation("删除订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "long", required = true, value = "门店标识", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户标识", defaultValue = "5")})
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public BaseJSON deleteOrder(long orderId) {

        BaseJSON baseJSON = new BaseJSON();
        int i = orderService.deleteOrder(orderId);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
        }
        return baseJSON;
    }

    @ApiOperation("更新订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "long", required = true, value = "订单id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "useEndTime", dataType = "String", required = true, value = "结束时间", defaultValue = "2019-0-22"),
            @ApiImplicitParam(paramType = "query", name = "useHours", dataType = "int", required = true, value = "使用时长", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "applyFee", dataType = "BigDecimal", required = true, value = "支付金额", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", dataType = "int", required = true, value = "订单状态", defaultValue = "10011002")})
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public BaseJSON updateOrder(long orderId, String useEndTime, int useHours, BigDecimal applyFee,int orderStatus) {

        BaseJSON baseJSON = new BaseJSON();
        OrderVO orderVo = new OrderVO();
        orderVo.setOrderId(orderId);
        orderVo.setUseHours(useHours);
        orderVo.setOrderStatus(orderStatus);
        try {
            orderVo.setUseEndTime(DateUtils.stringToDate(useEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = orderService.updateOrder(orderVo);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
        }
        return baseJSON;
    }
}
