package com.wework.base.service;

import com.wework.base.domain.vo.OrderVO;
import com.wework.base.domain.vo.StoreVO;

import java.util.List;

public interface OrderService {
    /**
     * 生成订单
     * @param storeId
     * @param userId
     */
    public int createOrder(long storeId, long userId);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    public int deleteOrder(long orderId);


    /**
     * 更新订单
     * @param orderVo
     * @return
     */
    public int updateOrder(OrderVO orderVo);
}
