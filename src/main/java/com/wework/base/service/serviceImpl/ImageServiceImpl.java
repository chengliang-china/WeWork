package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.config.OSSClientUtil;
import com.wework.base.domain.po.OrderTablePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.OrderDetailVO;
import com.wework.base.domain.vo.OrderVO;
import com.wework.base.mapper.OrderMapper;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.ImageService;
import com.wework.base.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OSSClientUtil ossClient;

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            try {
                throw new Exception("图片不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        return imgUrl;
    }
}
