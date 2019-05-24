package com.wework.base.controller;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.vo.StoreVO;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/store")
@Api(tags = { "门店模块" })
public class StoreController {
    @Autowired
    StoreService storeService;
    @ApiOperation("查找门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "longitude", dataType = "String", required = true, value = "经度", defaultValue = "116.40"),
            @ApiImplicitParam(paramType = "query", name = "latitude", dataType = "String", required = true, value = "维度", defaultValue = "39.90"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "String", required = false, value = "门店类型", defaultValue = "")})
    @RequestMapping(value = "/findStoreList", method = RequestMethod.POST)
    public BaseJSON findStoreList(String longitude, String latitude,String storeType) {

        BaseJSON baseJSON = new BaseJSON();
        List<StoreVO> storeList = storeService.findStoreList(longitude,latitude,storeType);
        baseJSON.setResult(storeList);
        return baseJSON;
    }

    @ApiOperation("评价门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "userNm", dataType = "long", required = true, value = "用户名", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "storeId", dataType = "long", required = true, value = "门店id", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "score", dataType = "BigDecimal", required = false, value = "评分", defaultValue = "4.5"),
            @ApiImplicitParam(paramType = "query", name = "description", dataType = "String", required = false, value = "评价内容", defaultValue = "未添加任何评价内容"),})
    @RequestMapping(value = "/saveStoreEvaluate", method = RequestMethod.POST)
    public BaseJSON saveStoreEvaluate(String token, String userNm, long storeId, BigDecimal score, String description) {
        BaseJSON baseJSON = new BaseJSON();
        StoreEvaluatePO po = new StoreEvaluatePO();
        po.setStoreId(storeId);
        po.setEvaluateName(userNm);
        po.setScore(score);
        po.setDescription(description);
        int i = storeService.saveStoreEvaluate(po);
        if(i<=0){
            baseJSON.setCode(1);
            baseJSON.setResult("失败");
            baseJSON.setMessage("创建评价失败，请联系管理员！");
        }
        return baseJSON;
    }
}
