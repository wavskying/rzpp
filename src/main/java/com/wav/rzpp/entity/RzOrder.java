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
public class RzOrder {
    private Long orderId;
    private String id;
    private Long employerId;
    private Long talentId;
    private String tid;
    private Integer hireTime;
    private Integer paymentState;
    private Integer hireMoney;
    private String orderState;
    private Long createTime;
    private Long manageUserId;
    private Long userId;
    private String talentName;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
        this.id = orderId.toString();
    }

    public void setTalentId(Long talentId) {
        this.talentId = talentId;
        this.tid = talentId.toString();
    }
}
