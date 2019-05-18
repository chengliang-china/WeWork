package com.wework.base.service;

import com.wework.base.domain.base.vo.StoreVO;

import java.util.List;

public interface StoreService {
    /**
     * 查询门店
     */
    public List<StoreVO> findStoreList();
}
