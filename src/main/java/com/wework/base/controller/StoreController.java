package com.wework.base.controller;
;
import com.alibaba.fastjson.JSON;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.service.OrderService;
import com.wework.base.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/store")
@Api(tags = { "门店模块" })
public class StoreController {
    public static final String url = "http://apis.haoservice.com/lifeservice/parking/getparking";
    public static final String key = "9bf345fc7b5c43e3b71d5ef7c276f030";
    @Autowired
    StoreService storeService;

    @Autowired
    OrderService orderService;
    @ApiOperation("查找门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "longitude", dataType = "String", required = true, value = "经度", defaultValue = "116.40"),
            @ApiImplicitParam(paramType = "query", name = "latitude", dataType = "String", required = true, value = "纬度", defaultValue = "39.90"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "String", required = false, value = "门店类型", defaultValue = "")})
    @RequestMapping(value = "/findStoreList", method = RequestMethod.POST)
    public BaseJSON findStoreList(String longitude, String latitude,String storeType) {

        BaseJSON baseJSON = new BaseJSON();
        List<StoreVO> storeList = storeService.findStoreList(longitude,latitude,storeType);
        baseJSON.setResult(storeList);
        return baseJSON;
    }

    @ApiOperation("查看门店明细")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "long", required = true, value = "门店id", defaultValue = "1")})
    @RequestMapping(value = "/findStoreDetail", method = RequestMethod.POST)
    public BaseJSON findStoreDetail(long storeId) {
        BaseJSON baseJSON = new BaseJSON();
        StoreDetailVO storeDetail = storeService.findStoreDetail(storeId);
        baseJSON.setResult(storeDetail);
        return baseJSON;
    }

    @ApiOperation("评价门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userNm", dataType = "Sting", required = true, value = "用户名", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "Long", required = true, value = "门店id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "Long", required = true, value = "订单id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "score", dataType = "BigDecimal", required = false, value = "评分", defaultValue = "4.5"),
            @ApiImplicitParam(paramType = "query", name = "description", dataType = "String", required = false, value = "评价内容", defaultValue = "未添加任何评价内容"),
            @ApiImplicitParam(paramType = "body", name = "listUrl", dataType = "String", required = false, value = "图片列表", defaultValue = "")})
    @RequestMapping(value = "/saveStoreEvaluate", method = RequestMethod.POST)
    public BaseJSON saveStoreEvaluate(String token, String userNm, Long storeId,Long orderId, BigDecimal score, String description,String listUrl) {
        BaseJSON baseJSON = new BaseJSON();
        StoreEvaluatePO po = new StoreEvaluatePO();
        po.setStoreId(storeId);
        po.setEvaluateName(userNm);
        po.setScore(score);
        po.setDescription(description);
        long a = storeService.saveStoreEvaluate(po);
        long id = po.getId();
        if(a<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("创建评价失败，请联系管理员！");
        }
        if(listUrl != null&&listUrl != ""){
            String decode = null;
            try {
                decode = URLDecoder.decode(listUrl,"UTF-8");
                List<String> list = JSON.parseObject(decode, List.class);
                System.out.println(list);
                if(list.size()>0){
                    storeService.saveStoreEvaluateImage(id,list);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //更新订单状态
        orderService.updateOrderFin(orderId);
        return baseJSON;
    }

    @ApiOperation("查看城市所有门店数量")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "")})
    @RequestMapping(value = "/findCityStoreNum", method = RequestMethod.GET)
    public BaseJSON findCityStoreNum() {
        BaseJSON baseJSON = new BaseJSON();
        List<CityStoreNumPO> records = storeService.findCityStoreNum();
        baseJSON.setResult(records);
        return baseJSON;
    }

    @ApiOperation("选择城市查看门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "city", dataType = "String", required = true, value = "城市名", defaultValue = "北京市"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "String", required = false, value = "门店类型", defaultValue = "")})
@RequestMapping(value = "/findCityStore", method = RequestMethod.GET)
    public BaseJSON findCityStore(String city,String storeType) {
        BaseJSON baseJSON = new BaseJSON();
        List<StoreVO> storeList = storeService.findCityStore(city,storeType);
        baseJSON.setResult(storeList);
        return baseJSON;
    }

    @ApiOperation("获取附近停车场")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "location", dataType = "String", required = true, value = "location", defaultValue = "116.422396,40.039667")})
    @RequestMapping(value = "/getNearbyParking", method = RequestMethod.GET)
    public JSONObject getNearbyParking(String location) {
        JSONObject json = null;
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
                    .append("location=").append(location)
                    .append("&key=").append(key);
            // 写入参数
            conn.getOutputStream().write(params.toString().getBytes());
            // 读入响应
            String response = StreamUtils.copyToString(
                    conn.getInputStream(), Charset.forName("UTF-8"));
            json = JSONObject.fromObject(response);
        } catch (ProtocolException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }
}
