package com.wework.base.service;

import com.wework.base.domain.vo.StoreVO;

import java.util.List;

public interface OrderService {
    /**
     * 生成订单
     * @param storeId
     * @param userId
     */
    public int createOrder(long storeId, long userId);
}
