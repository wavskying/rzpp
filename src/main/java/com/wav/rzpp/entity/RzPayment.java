package com.wav.rzpp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hbw
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzPayment {
    private Long paymentId;
    private Long orderId;
    private Integer paymentMoney;
    private Long createTime;
}
