package com.wework.base.mapper;

import com.wework.base.domain.po.OrderTablePO;
import com.wework.base.domain.po.StorePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门店mapper
 */
@Mapper
@Repository(value="OrderMapper")
public interface OrderMapper {

	public int createOrder(@Param("po")OrderTablePO po);

	public int deleteOrder(@Param("orderId")long orderId);

	public int updateOrder(@Param("po")OrderTablePO po);

	public List<OrderTablePO> getOrderList(@Param("userId")long userId);

	public int checkUserUnfinishOrder(@Param("userId")long userId);

    int updateUseIntegral(@Param("userId")long userId, @Param("integral") BigDecimal integral);
}
