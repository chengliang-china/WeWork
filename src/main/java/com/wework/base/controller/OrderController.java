package com.wework.base.controller;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.service.OrderService;
import com.wework.base.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
        int i = orderService.createOrder(storeId,userId);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
        }
        return baseJSON;
    }
}
