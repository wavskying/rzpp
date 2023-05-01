package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzOrder;
import com.wav.rzpp.mapper.RzOrderMapper;
import com.wav.rzpp.service.RzOrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzOrderServiceImpl implements RzOrderService {
    @Autowired
    RzOrderMapper rzOrderMapper;

    @Override
    public Integer addOrder(RzOrder rzOrder) {
        return rzOrderMapper.addOrder(rzOrder);
    }

    @Override
    public List<RzOrder> getOrderListByUserId(String userId) {
        return rzOrderMapper.getOrderListByUserId(userId);
    }

    @Override
    public List<RzOrder> getAllOrderList() {
        return rzOrderMapper.getAllOrderList();
    }

    @Override
    public void updateOrderById(String orderId, String state) {
        rzOrderMapper.updateOrderById(orderId, state);
    }
}
