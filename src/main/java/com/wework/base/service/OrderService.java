package com.wework.base.service;

import com.wework.base.domain.vo.OrderDetailVO;
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

    /**
     * 查找订单
     * @return
     */
    public List<OrderDetailVO> findOrderList(long userId);
    /**
     * 根据状态查询用户订单数量
     * @param userId
     * @return
     */
    public int findOrderNumByStatus(long userId, int orderStatus);

    /**
     * 完成订单
     * @param orderId
     */
    public void updateOrderFin(Long orderId);
}
