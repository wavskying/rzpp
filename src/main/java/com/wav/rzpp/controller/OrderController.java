package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzOrder;
import com.wav.rzpp.service.RzOrderService;
import com.wav.rzpp.utils.SnowFlake;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("订单模块")
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RzOrderService rzOrderService;

    @PostMapping("/addOrder")
    public AjaxResult addOrder(RzOrder rzOrder) {
        rzOrder.setOrderId(SnowFlake.nextId());
        rzOrder.setCreateTime(System.currentTimeMillis());
        rzOrder.setPaymentState(1);
        rzOrder.setOrderState("1");
        rzOrderService.addOrder(rzOrder);
        return AjaxResult.success("支付成功");
    }

    @PostMapping("/getOrderListByUserId")
    public AjaxResult getOrderListByUserId(String userId) {
        List<RzOrder> rzOrderList = rzOrderService.getOrderListByUserId(userId);
        return AjaxResult.success(rzOrderList);
    }

    @PostMapping("/getAllOrderList")
    public AjaxResult getAllOrderList() {
        List<RzOrder> rzOrderList = rzOrderService.getAllOrderList();
        return AjaxResult.success(rzOrderList);
    }

    @PostMapping("/updateOrderById")
    public AjaxResult updateOrderById(@RequestParam String orderId, @RequestParam String state) {
        rzOrderService.updateOrderById(orderId,state);
        return AjaxResult.success();
    }
}
