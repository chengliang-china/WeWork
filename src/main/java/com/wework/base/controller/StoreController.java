package com.wework.base.controller;
;
import com.alibaba.fastjson.JSON;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.CarouselMapPO;
import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.service.ImageService;
import com.wework.base.service.OrderService;
import com.wework.base.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.charset.Charset;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ImageService imageService;

    @ApiOperation("新增门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "storeName", dataType = "Sting", required = true, value = "门店名", defaultValue = "wakup门店"),
            @ApiImplicitParam(paramType = "query", name = "applyFee", dataType = "BigDecimal", required = true, value = "每小时收费金额", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "arrivalWay", dataType = "String", required = true, value = "到达方式", defaultValue = "地铁二号线"),
            @ApiImplicitParam(paramType = "query", name = "openStartTime", dataType = "Time", required = true, value = "工作日开始营业时间", defaultValue = "08:00:00"),
            @ApiImplicitParam(paramType = "query", name = "openEndTime", dataType = "Time", required = true, value = "工作日接结束营业时间", defaultValue = "22:00:00"),
            @ApiImplicitParam(paramType = "query", name = "offStartTime", dataType = "Time", required = true, value = "非工作日开始营业时间", defaultValue = "00:00:00"),
            @ApiImplicitParam(paramType = "query", name = "offEndTime", dataType = "Time", required = true, value = "非工作日接结束营业时间", defaultValue = "23:00:00"),
            @ApiImplicitParam(paramType = "query", name = "longitude", dataType = "BigDecimal", required = true, value = "经度", defaultValue = "121.46626"),
            @ApiImplicitParam(paramType = "query", name = "latitude", dataType = "BigDecimal", required = true, value = "纬度", defaultValue = "31.22046"),
            @ApiImplicitParam(paramType = "query", name = "seatNum", dataType = "Integer", required = true, value = "座位数量", defaultValue = "100"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "Integer", required = true, value = "门店类型", defaultValue = "10013001"),
            @ApiImplicitParam(paramType = "query", name = "city", dataType = "String", required = true, value = "门店城市", defaultValue = "上海市"),
            @ApiImplicitParam(paramType = "query", name = "storeIntroduction", dataType = "String", required = true, value = "门店简介", defaultValue = "wakup门店"),
            @ApiImplicitParam(paramType = "query", name = "thumbnailUrl", dataType = "String", required = true, value = "门店缩略图", defaultValue = "")})
    @RequestMapping(value = "/saveStoreInfo", method = RequestMethod.POST)
    public BaseJSON saveStoreInfo(String storeName, BigDecimal applyFee, String arrivalWay, Time openStartTime, Time offEndTime, Time offStartTime, Time openEndTime,BigDecimal longitude,BigDecimal latitude,Integer seatNum, Integer storeType,String storeIntroduction, String thumbnailUrl,String city) {
        BaseJSON baseJSON = new BaseJSON();
        StorePO po = new StorePO();
        po.setStoreName(storeName);
        po.setApplyFee(applyFee);
        po.setArrivalWay(arrivalWay);
        po.setOpenStartTime(openStartTime);
        po.setOpenEndTime(openEndTime);
        po.setOffStartTime(offStartTime);
        po.setOffEndTime(offEndTime);
        po.setLongitude(longitude);
        po.setLatitude(latitude);
        po.setStoreType(storeType);
        po.setSeatNum(seatNum);
        po.setStoreIntroduction(storeIntroduction);
        po.setThumbnailUrl(thumbnailUrl);
        po.setCity(city);
        storeService.saveStoreinfo(po);
        return baseJSON;
    }

    @ApiOperation("更新门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "Long", required = true, value = "门店id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "storeName", dataType = "Sting", required = true, value = "门店名", defaultValue = "wakup门店"),
            @ApiImplicitParam(paramType = "query", name = "applyFee", dataType = "BigDecimal", required = true, value = "每小时收费金额", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "arrivalWay", dataType = "String", required = true, value = "到达方式", defaultValue = "地铁二号线"),
            @ApiImplicitParam(paramType = "query", name = "openStartTime", dataType = "Time", required = true, value = "工作日开始营业时间", defaultValue = "08:00:00"),
            @ApiImplicitParam(paramType = "query", name = "openEndTime", dataType = "Time", required = true, value = "工作日接结束营业时间", defaultValue = "22:00:00"),
            @ApiImplicitParam(paramType = "query", name = "offStartTime", dataType = "Time", required = true, value = "非工作日开始营业时间", defaultValue = "00:00:00"),
            @ApiImplicitParam(paramType = "query", name = "offEndTime", dataType = "Time", required = true, value = "非工作日接结束营业时间", defaultValue = "23:00:00"),
            @ApiImplicitParam(paramType = "query", name = "longitude", dataType = "BigDecimal", required = true, value = "经度", defaultValue = "121.46626"),
            @ApiImplicitParam(paramType = "query", name = "latitude", dataType = "BigDecimal", required = true, value = "纬度", defaultValue = "31.22046"),
            @ApiImplicitParam(paramType = "query", name = "seatNum", dataType = "Integer", required = true, value = "座位数量", defaultValue = "100"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "Integer", required = true, value = "门店类型", defaultValue = "10013001"),
            @ApiImplicitParam(paramType = "query", name = "city", dataType = "String", required = true, value = "门店城市", defaultValue = "上海市"),
            @ApiImplicitParam(paramType = "query", name = "storeIntroduction", dataType = "String", required = true, value = "门店简介", defaultValue = "wakup门店"),
            @ApiImplicitParam(paramType = "query", name = "thumbnailUrl", dataType = "String", required = true, value = "门店缩略图", defaultValue = "")})
    @RequestMapping(value = "/updateStoreInfo", method = RequestMethod.POST)
    public BaseJSON updateStoreInfo(Long storeId,String storeName, BigDecimal applyFee, String arrivalWay, Time openStartTime, Time openEndTime,Time offEndTime, Time offStartTime, BigDecimal longitude,BigDecimal latitude,Integer seatNum, Integer storeType,String storeIntroduction, String thumbnailUrl,String city) {
        BaseJSON baseJSON = new BaseJSON();
        StorePO po = new StorePO();
        po.setStoreId(storeId);
        po.setStoreName(storeName);
        po.setApplyFee(applyFee);
        po.setArrivalWay(arrivalWay);
        po.setOpenStartTime(openStartTime);
        po.setOpenEndTime(openEndTime);
        po.setOffStartTime(offStartTime);
        po.setOffEndTime(offEndTime);
        po.setLongitude(longitude);
        po.setLatitude(latitude);
        po.setStoreType(storeType);
        po.setSeatNum(seatNum);
        po.setStoreIntroduction(storeIntroduction);
        po.setThumbnailUrl(thumbnailUrl);
        po.setCity(city);
        storeService.updateStoreInfo(po);
        return baseJSON;
    }
    @ApiOperation("门店添加图片")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "Long", required = true, value = "门店id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "url", dataType = "String", required = true, value = "图片url", defaultValue = "http://wakeup1.oss-cn-beijing.aliyuncs.com/data/1560652556316.jpg?Expires=1876012555&OSSAccessKeyId=LTAIg5AbTypJW01w&Signature=kRfTrTTeEjQCswW%2Be7itqVoYnQA%3D")})
    @RequestMapping(value = "/saveStoreImage", method = RequestMethod.POST)
    public BaseJSON saveStoreInfo(Long storeId,String url) {
        BaseJSON baseJSON = new BaseJSON();
        storeService.saveStoreimage(storeId,url);
        return baseJSON;
    }

    @ApiOperation("删除门店")
    @ApiImplicitParams(value = {@ApiImplicitParam(paramType = "query", name = "storeId", dataType = "Long", required = true, value = "门店id", defaultValue = "")})
    @RequestMapping(value = "/deleteStore", method = RequestMethod.GET)
    public BaseJSON deleteStore( Long storeId) {
        BaseJSON baseJSON = new BaseJSON();
        storeService.deleteStroe(storeId);
        return baseJSON;
    }
    @Autowired
    OrderService orderService;
    @ApiOperation("查找门店")
    @RequestMapping(value = "/findStoreList", method = RequestMethod.GET)
    public BaseJSON findStoreList() {

        BaseJSON baseJSON = new BaseJSON();
        List<StoreVO> storeList = storeService.findStoreList();
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

    @ApiOperation("首页轮播图查询")
    @RequestMapping(value = "/getHomeImage", method = RequestMethod.GET)
    public BaseJSON getHomeImage() {
        BaseJSON baseJSON = new BaseJSON();
        CarouselMapPO carouselMapPo = storeService.getHomeImage();
        baseJSON.setResult(carouselMapPo);
        return baseJSON;
    }

    @ApiOperation("首页轮播图修改")
    @RequestMapping(value = "/updateHomeImage", method = RequestMethod.POST)
    @ResponseBody
    public BaseJSON updateHomeImage(HttpServletRequest request, MultipartFile activeImage,MultipartFile couponImage,MultipartFile generalImage) {
        BaseJSON baseJSON = new BaseJSON();
        if(activeImage== null  || couponImage == null || generalImage == null){
            baseJSON.setCode(1);
            baseJSON.setMessage("三张图片不可为空");
            return baseJSON;
        }
        try {
            String head1 = imageService.uploadImage(activeImage);//此处是调用上传服务接口
            String head2 = imageService.uploadImage(couponImage);//此处是调用上传服务接口
            String head3 = imageService.uploadImage(generalImage);//此处是调用上传服务接口
            CarouselMapPO carouselMapPo = new CarouselMapPO();
            carouselMapPo.setId(1);
            carouselMapPo.setActiveImage(head1);
            carouselMapPo.setCouponImage(head2);
            carouselMapPo.setGeneralImage(head3);
            storeService.updateHomeImage(carouselMapPo);
        } catch (Exception e) {
            e.printStackTrace();
            baseJSON.setCode(1);
            baseJSON.setMessage("图片上传失败");
        }
        return baseJSON;
    }

    @ApiOperation("门店类型查询")
    @RequestMapping(value = "/getStoreTypes", method = RequestMethod.GET)
    public BaseJSON getStoreTypes() {
        BaseJSON baseJSON = new BaseJSON();
        List<Map<String,Object>> list = storeService.getStoreTypes();
        baseJSON.setResult(list);
        return baseJSON;
    }

    @ApiOperation("门店类型修改")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "one", dataType = "String", required = true, value = "第一个", defaultValue = "工位"),
            @ApiImplicitParam(paramType = "query", name = "two", dataType = "String", required = true, value = "第二个", defaultValue = "会议室"),
            @ApiImplicitParam(paramType = "query", name = "three", dataType = "String", required = true, value = "第三个", defaultValue = "办公厅"),
            @ApiImplicitParam(paramType = "query", name = "four", dataType = "String", required = true, value = "第四个", defaultValue = "路演厅")})
    @RequestMapping(value = "/updateStoreTypes", method = RequestMethod.POST)
    public BaseJSON updateStoreTypes(String one,String two,String three,String four) {
        BaseJSON baseJSON = new BaseJSON();
        storeService.updateStoreTypes(one,two,three,four);
        return baseJSON;
    }
}
