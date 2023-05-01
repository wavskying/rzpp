package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzOrderMapper {

    Integer addOrder(@Param("rzOrder") RzOrder rzOrder);

    List<RzOrder> getOrderListByUserId(String userId);

    List<RzOrder> getAllOrderList();

    void updateOrderById(String orderId, String state);
}
