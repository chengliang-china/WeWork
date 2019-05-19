package com.wework.base.service;

import com.wework.base.domain.vo.StoreVO;

import java.util.List;

public interface StoreService {
    /**
     * 查询门店
     * @param longitude
     * @param latitude
     * @param storeType
     */
    public List<StoreVO> findStoreList(String longitude, String latitude, String storeType);
}
