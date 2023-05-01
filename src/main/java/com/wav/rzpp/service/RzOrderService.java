package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
public interface RzOrderService {
    Integer addOrder(@Param("rzOrder") RzOrder rzOrder);

    List<RzOrder> getOrderListByUserId(String userId);

    List<RzOrder> getAllOrderList();

    void updateOrderById(String orderId, String state);
}
