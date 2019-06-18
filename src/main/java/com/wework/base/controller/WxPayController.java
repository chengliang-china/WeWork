package com.wework.base.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.wework.base.config.MyWxPayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableSwagger2
@RequestMapping("/WXpay")
@Api(tags = { "微信支付" })
public class WxPayController {

    @ApiOperation("统一下单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "outTradeNo", dataType = "String", required = true, value = "outTradeNo", defaultValue = "")})
    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.GET)
    public void unifiedOrder(@Param("token") String token,@Param("outTradeNo") String outTradeNo) throws Exception {
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.HMACSHA256);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "", dataType = "String", required = true, value = "", defaultValue = "")})
    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public void searchOrder(@Param("token") String token) throws Exception {
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.HMACSHA256);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = wxpay.orderQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("退款查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "", dataType = "String", required = true, value = "", defaultValue = "")})
    @RequestMapping(value = "/refundOrder", method = RequestMethod.GET)
    public void refundOrder(@Param("token") String token) throws Exception {
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.HMACSHA256);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = wxpay.refundQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("下载对账单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "", dataType = "String", required = true, value = "", defaultValue = "")})
    @RequestMapping(value = "/downloadBill", method = RequestMethod.GET)
    public void downloadBill(@Param("token") String token) throws Exception {
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.HMACSHA256);

        Map<String, String> data = new HashMap<String, String>();
        data.put("bill_date", "20140603");
        data.put("bill_type", "ALL");

        try {
            Map<String, String> resp = wxpay.downloadBill(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
