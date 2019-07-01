package com.wework.base.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.wework.base.config.MyWxPayConfig;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@RestController
@EnableSwagger2
@RequestMapping("/WXpay")
@Api(tags = { "微信支付" })
public class WxPayController {

    @ApiOperation("统一下单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "outTradeNo", dataType = "String", required = true, value = "outTradeNo", defaultValue = "no1111111"),
            @ApiImplicitParam(paramType = "query", name = "totalFee", dataType = "String", required = true, value = "totalFee", defaultValue = "12"),
            @ApiImplicitParam(paramType = "query", name = "openid", dataType = "String", required = true, value = "openid", defaultValue = "oGio_5ck0rdvmqGbRL1fR2XcAfow"),
            @ApiImplicitParam(paramType = "query", name = "spbillCreateIp", dataType = "String", required = true, value = "spbillCreateIp", defaultValue = "39.100.80.242")})
    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.GET)
    public BaseJSON unifiedOrder(@Param("token") String token,@Param("outTradeNo") String outTradeNo,@Param("totalFee") String totalFee,@Param("openid") String openid,@Param("spbillCreateIp") String spbillCreateIp) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        MyWxPayConfig config = new MyWxPayConfig();
        //String nonceStr = WXPayUtil.generateNonceStr();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
        Map<String, String> data = new HashMap<String, String>();

        data.put("body", "wakup");
        data.put("out_trade_no", outTradeNo);
        data.put("fee_type", "CNY");
        data.put("total_fee", totalFee);
        data.put("spbill_create_ip", spbillCreateIp);
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "JSAPI");
        data.put("openid", openid);
        Map<String, String> resp = new HashMap<String, String>();
        try {
            resp = wxpay.unifiedOrder(data);
            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //resp.put("timeStamp",timeStamp);
        baseJSON.setResult(resp);
        return baseJSON;
    }

    @ApiOperation("页面调用支付所用签名paySign")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "package1", dataType = "String", required = true, value = "统一下单prepay_id", defaultValue = "")})
    @RequestMapping(value = "/getPaySign", method = RequestMethod.GET)
    public BaseJSON getPaySign(@Param("token") String token, @Param("package1") String package1) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        Date d = new Date();
        String timeStamp = d.getTime() / 1000 + "";   //getTime()得到的是微秒， 需要换算成秒
        MyWxPayConfig config = new MyWxPayConfig();
        Map<String, String> signMap = new HashMap<String, String>();
        signMap.put("appId", config.getAppID());
        signMap.put("timeStamp", timeStamp);
        signMap.put("nonceStr", WXPayUtil.generateNonceStr());
        signMap.put("package", "prepay_id="+package1);
        signMap.put("signType",  "MD5");
        String paySign = WXPayUtil.generateSignature(signMap,config.getKey(), WXPayConstants.SignType.MD5);
        signMap.put("paySign",paySign);
        //System.out.println(paySign);
        baseJSON.setResult(signMap);
        return baseJSON;
    }
    @ApiOperation("撤回订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "outTradeNo", dataType = "String", required = true, value = "outTradeNo", defaultValue = "no000012")})
    @RequestMapping(value = "/reverse", method = RequestMethod.GET)
    public BaseJSON reverse(@Param("token") String token,@Param("outTradeNo") String outTradeNo) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);
        try {
            Map<String, String> resp = wxpay.reverse(data);
            baseJSON.setResult(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseJSON;
    }
    @ApiOperation("订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "outTradeNo", dataType = "String", required = true, value = "outTradeNo", defaultValue = "no0001211")})
    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public BaseJSON searchOrder(@Param("token") String token,@Param("outTradeNo") String outTradeNo) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);
        try {
            Map<String, String> resp = wxpay.orderQuery(data);
            baseJSON.setResult(resp);
            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseJSON;
    }

    @ApiOperation("退款查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "outTradeNo", dataType = "String", required = true, value = "outTradeNo", defaultValue = "no000012")})
    @RequestMapping(value = "/refundOrder", method = RequestMethod.GET)
    public BaseJSON refundOrder(@Param("token") String token,@Param("outTradeNo") String outTradeNo) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);

        try {
            Map<String, String> resp = wxpay.refundQuery(data);
            baseJSON.setResult(resp);
            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseJSON;
    }

    @ApiOperation("下载对账单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "billDate", dataType = "String", required = true, value = "billDate", defaultValue = "20140603")})
    @RequestMapping(value = "/downloadBill", method = RequestMethod.GET)
    public BaseJSON downloadBill(@Param("token") String token,@Param("billDate") String billDate) throws Exception {
        BaseJSON baseJSON  = new BaseJSON();
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
        Map<String, String> data = new HashMap<String, String>();
        data.put("bill_date", billDate);
        data.put("bill_type", "ALL");
        try {
            Map<String, String> resp = wxpay.downloadBill(data);
            baseJSON.setResult(resp);
            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseJSON;
    }
}
