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
public class RzMessage {
    private Long messageId;
    private Long senderId;
    private Long receiveId;
    private String content;
    private Long createTime;
}
