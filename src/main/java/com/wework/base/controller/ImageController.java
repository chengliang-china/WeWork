package com.wework.base.controller;

import com.wework.base.service.ImageService;
import com.wework.base.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableSwagger2
@RequestMapping("/image")
@Api(tags = { "图片模块" })
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/headImgUpload.json", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> headImgUpload(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");
        try {
            String head = imageService.uploadImage(file, 4);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
            value.put("data", head);
        } catch (Exception e) {
            e.printStackTrace();
            value.put("success", false);
            value.put("errorCode", 200);
            value.put("errorMsg", "图片上传失败");
        }
        return value;
    }
}
