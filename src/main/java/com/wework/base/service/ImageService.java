package com.wework.base.service;

import com.wework.base.domain.vo.OrderDetailVO;
import com.wework.base.domain.vo.OrderVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    /**
     * 上传图片
     * @param file
     * @param userId
     * @return
     */
    public String uploadImage(MultipartFile file) throws Exception;
}
