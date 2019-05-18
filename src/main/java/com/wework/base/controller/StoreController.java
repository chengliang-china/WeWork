package com.wework.base.controller;

import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.base.vo.StoreVO;
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
@RequestMapping("/store")
@Api(tags = { "门店模块" })
public class StoreController {
    @Autowired
    StoreService storeService;
    @ApiOperation("查找门店")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "longitude", dataType = "String", required = true, value = "经度", defaultValue = "116.40"),
            @ApiImplicitParam(paramType = "query", name = "latitude", dataType = "String", required = true, value = "维度", defaultValue = "39.90"),
            @ApiImplicitParam(paramType = "query", name = "storeType", dataType = "String", required = false, value = "门店类型", defaultValue = "")})
    @RequestMapping(value = "/findStoreList", method = RequestMethod.POST)
    public BaseJSON findStoreList(String longitude, String latitude,String storeType) {

        BaseJSON baseJSON = new BaseJSON();
        List<StoreVO> storeList = storeService.findStoreList();
        //baseJSON.setResult(account + "-" + password);
        return baseJSON;
    }
}
