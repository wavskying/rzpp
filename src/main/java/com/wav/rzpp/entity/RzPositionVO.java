package com.wav.rzpp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: hbw
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzPositionVO {

    private Long positionId;

    private String name;

    private Long createTime;

    private Long pid;

    private String description;

    private List<RzPositionVO> children;
}
