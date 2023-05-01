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
public class RzComment {
    private Long commentId;
    private Long talentId;
    private Long userId;
    private String content;
    private String rate;
    private Long createTime;
}
