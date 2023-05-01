package com.wav.rzpp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: hbw
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private List<T> data;  // 当前页数据
    private int totalCount;  // 总数据量
    private int pageSize;  // 每页数据量
    private int pageNum;  // 当前页码
}
