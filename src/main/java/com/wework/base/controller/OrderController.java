package com.wework.base.controller;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.OrderDetailVO;
import com.wework.base.domain.vo.OrderVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.service.OrderService;
import com.wework.base.service.RedisService;
import com.wework.base.service.StoreService;
import com.wework.base.service.UserService;
import com.wework.base.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/order")
@Api(tags = { "订单模块" })
public class OrderController {

    @Autowired
    private OrderService orderService;

    public static final String url = "http://www.papershanghai.com:8088/api/door/remoteOpenById";
    public static final String key = "RTg5RDU4QzA2Qjg4OTA5NkU2RDkyQz";

    @Autowired
    private RedisService redisService;

    @ApiOperation("扫码接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "doorId", dataType = "long", required = true, value = "门标识", defaultValue = "8")})
    @RequestMapping(value = "/scanCode", method = RequestMethod.GET)
    public String scanCode(long doorId) {
        String response = null;
        try {
            // 创建一个URL对象
            URL targetUrl = new URL(url);
            // 从URL对象中获得一个连接对象
            HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
            // 设置请求方式 注意这里的POST或者GET必须大写
            conn.setRequestMethod("POST");
            // 设置POST请求是有请求体的
            conn.setDoOutput(true);
            // 拼接发送的短信内容
            StringBuilder params = new StringBuilder(100)
                    .append("doorId=").append(8)
                    .append("&interval=").append(5)
                    .append("&access_token=").append(key);

            // 写入参数
            conn.getOutputStream().write(params.toString().getBytes());
            // 读入响应
            response = StreamUtils.copyToString(
                    conn.getInputStream(), Charset.forName("UTF-8"));

            //baseJSON.setResult(response);
        } catch (ProtocolException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;

    }
    @ApiOperation("扫码生成订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "long", required = true, value = "门店标识", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户标识", defaultValue = "5")})
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public BaseJSON createOrder(long storeId, long userId)  {

        BaseJSON baseJSON = new BaseJSON();
        //检测该用户是否存在未结算订单
        int count = orderService.findOrderNumByStatus(userId,BaseCode.ORDER_OPENED);
        if(count>0){
            baseJSON.setMessage("门已打开，用户存在未结算订单，请先结算上一次订单！");
            return baseJSON;
        }
        //生成订单
        int i = orderService.createOrder(storeId,userId);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("创建订单失败，请联系管理员！");
        }
        return baseJSON;

    }

    @ApiOperation("删除订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "long", required = true, value = "订单id", defaultValue = "1")})
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public BaseJSON deleteOrder(long orderId) {

        BaseJSON baseJSON = new BaseJSON();
        int i = orderService.deleteOrder(orderId);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("删除订单失败，请联系管理员！");
        }
        return baseJSON;
    }

    @ApiOperation("更新订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "long", required = true, value = "订单id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "useEndTime", dataType = "String", required = true, value = "结束时间", defaultValue = "2019-10-22 10:08:00"),
            @ApiImplicitParam(paramType = "query", name = "useHours", dataType = "BigDecimal", required = true, value = "使用时长", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "couponId", dataType = "long", required = false, value = "优惠券id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "applyFee", dataType = "BigDecimal", required = true, value = "支付金额", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", dataType = "int", required = true, value = "订单状态", defaultValue = "10011002")})
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public BaseJSON updateOrder(String token,long orderId, String useEndTime, BigDecimal useHours,Long couponId, BigDecimal applyFee,int orderStatus) {

        BaseJSON baseJSON = new BaseJSON();
        UserPO userPO = (UserPO) redisService.get(token);
        OrderVO orderVo = new OrderVO();
        orderVo.setOrderId(orderId);
        orderVo.setUserId(userPO.getUserId());
        orderVo.setUseHours(useHours);
        orderVo.setOrderStatus(orderStatus);
        orderVo.setApplyFee(applyFee);
        if(couponId !=null&&couponId !=0){
            orderVo.setCouponId(couponId);
        }
        try {
            orderVo.setUseEndTime(DateUtils.stringToDateTime(useEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = orderService.updateOrder(orderVo);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("更新订单失败，请联系管理员！");
        }
        return baseJSON;
    }

    @ApiOperation("查询用户订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户标识", defaultValue = "5")})
    @RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
    public BaseJSON searchOrder(String token,long userId) {

        BaseJSON baseJSON = new BaseJSON();
        List<OrderDetailVO> list = orderService.findOrderList(userId);
        baseJSON.setResult(list);
        return baseJSON;
    }

    @ApiOperation("查询用户未评价订单数量")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "long", required = true, value = "用户标识", defaultValue = "5")})
    @RequestMapping(value = "/searchUnEvaluateOrderNum", method = RequestMethod.POST)
    public BaseJSON searchUnEvaluateOrderNum(String token,long userId) {
        BaseJSON baseJSON = new BaseJSON();
        int num  = orderService.findOrderNumByStatus(userId,BaseCode.ORDER_SETTLED);
        baseJSON.setResult(num);
        return baseJSON;
    }

    @ApiOperation("结算订单接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "useStartTime", dataType = "String", required = true, value = "开始使用时间", defaultValue = "2019-06-16 10:08:45"),
            @ApiImplicitParam(paramType = "query", name = "useEndTime", dataType = "String", required = true, value = "结束使用时间", defaultValue = "2019-06-16 15:08:55"),
            @ApiImplicitParam(paramType = "query", name = "applyFee", dataType = "BigDecimal", required = true, value = "单价/小时为单位", defaultValue = "9.9")})
    @RequestMapping(value = "/settlementOrder", method = RequestMethod.POST)
    public BaseJSON settlementOrder(String useStartTime,String useEndTime,BigDecimal applyFee) {
        BaseJSON baseJSON = new BaseJSON();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        //价格计算
        try {
            Date useStart = DateUtils.stringToDateTime(useStartTime);
            Date useEnd = DateUtils.stringToDateTime(useEndTime);

            long diff = useEnd.getTime() - useStart.getTime();
            // 计算差多少天
            long day = diff / nd;
            // 计算差多少小时
            long hour = day*24+diff % nd / nh;
            // 计算差多少分钟
            long min = diff % nd % nh / nm;

            if(min<=5&&hour==0) {
                baseJSON.setResult(0);
            }else{
                if(min>0){
                    BigDecimal fee = applyFee.multiply(new BigDecimal(hour+1));
                    baseJSON.setResult(fee);
                }else{
                    BigDecimal fee = applyFee.multiply(new BigDecimal(hour));
                    baseJSON.setResult(fee);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return baseJSON;
    }
}
