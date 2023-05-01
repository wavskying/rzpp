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
public class RzSession {
    private Long sessionId;
    private Long senderId;
    private Long receiveId;
    private String lastMessage;
    private Integer unRead;
    private Long createTime;
}
