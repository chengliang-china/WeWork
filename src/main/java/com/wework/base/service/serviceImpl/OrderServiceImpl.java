package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.po.OrderTablePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.mapper.OrderMapper;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int createOrder(long storeId, long userId) {

        OrderTablePO orderTablePO = new OrderTablePO();
        orderTablePO.setStoreId(storeId);
        orderTablePO.setUserId(userId);
        orderTablePO.setUseStartTime(new Date());
        orderTablePO.setOrderStatus(BaseCode.ORDER_OPENED);
        return orderMapper.createOrder(orderTablePO);
    }
}
