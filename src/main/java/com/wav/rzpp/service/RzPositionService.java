package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
public interface RzPositionService {
    /**
     * 插入单条数据
     *
     * @param rzPosition 实体类数据
     * @return 成功条数
     */
    Integer addRzPosition(@Param("rzPosition") RzPosition rzPosition);

    /**
     * 批量插入
     *
     * @param rzPositionList List集合数据
     * @return 成功条数
     */
    Integer addRzPositionList(@Param("rzPositionList") List<RzPosition> rzPositionList);

    List<Map<String,Object>> getAllRzPosition();

    void updatePosition(String id, String name);

    void deletePosition(String id);
}
