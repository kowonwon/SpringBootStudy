package com.payment.app.mapper;
import org.apache.ibatis.annotations.*;

import com.payment.app.domain.Order;
@Mapper
public interface OrderMapper {
	@Select("SELECT * FROM orders WHERE order_id = #{orderId}")
	Order findByOrderId(@Param("orderId") String orderId);
}
