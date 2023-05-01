package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzPosition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzPositionMapper {

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


    /**
     * 查询所有的RzPosition数据
     *
     * @return RzPosition列表
     */
    List<RzPosition> getAllRzPosition();

    void updatePosition(String id, String name);

    void deletePosition(String id);

}
