package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.po.OrderTablePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.OrderDetailVO;
import com.wework.base.domain.vo.OrderVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.mapper.OrderMapper;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int createOrder(long storeId, long userId) {

        OrderTablePO orderTablePO = new OrderTablePO();
        orderTablePO.setStoreId(storeId);
        orderTablePO.setUserId(userId);
        orderTablePO.setUseStartTime(new Date());
        orderTablePO.setOrderStatus(BaseCode.ORDER_OPENED);
        return orderMapper.createOrder(orderTablePO);
    }

    @Override
    public int deleteOrder(long orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    @Override
    public int updateOrder(OrderVO orderVo) {
        int orderStatus = orderVo.getOrderStatus();
        BigDecimal applyFee = orderVo.getApplyFee();
        BigDecimal integral = applyFee.divide(new BigDecimal(100));
        OrderTablePO orderTablePo =  new OrderTablePO();
        orderTablePo.setOrderId(orderVo.getOrderId());
        orderTablePo.setUseEndTime(orderVo.getUseEndTime());
        orderTablePo.setApplyFee(applyFee);
        orderTablePo.setUseHours(orderVo.getUseHours());
        orderTablePo.setOrderStatus(orderStatus);
        orderTablePo.setCouponId(orderVo.getCouponId());
        int a = orderMapper.updateOrder(orderTablePo);//更新订单
        if(orderStatus == BaseCode.ORDER_COMPLETED){
            orderMapper.updateUseIntegral(orderVo.getUserId(),integral);
        }
        return a;
    }

    @Override
    public List<OrderDetailVO> findOrderList(long userId) {
        List<OrderTablePO> list = orderMapper.getOrderList(userId);
        List<OrderDetailVO> list1 = new ArrayList<>();
        if(list.size() <=0){
            return null;
        }
        for(OrderTablePO po : list){
            StorePO storePO = storeMapper.findStoreDetail(po.getStoreId());
            OrderDetailVO vo = new OrderDetailVO();
            vo.setStoreNm(storePO.getStoreName());
            vo.setLatitude(storePO.getLatitude());
            vo.setLongitude(storePO.getLongitude());
            vo.setApplyFee(storePO.getApplyFee());
            vo.setOrderId(po.getOrderId());
            vo.setStoreId(po.getStoreId());
            vo.setUseHours(po.getUseHours());
            vo.setOrderStatus(po.getOrderStatus());
            vo.setUseStartTime(po.getUseStartTime());
            vo.setUseEndTime(po.getUseEndTime());
            list1.add(vo);
        }
        return list1;
    }

    @Override
    public int findOrderNumByStatus(long userId,int orderStatus) {
        return orderMapper.findOrderNumByStatus(userId,orderStatus);
    }
}
