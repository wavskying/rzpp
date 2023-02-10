package com.wav.rzpp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzRole {

    private Long roleId;
    private String roleName;
    private Long createTime;

}
