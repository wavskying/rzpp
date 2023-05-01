package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzTalent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
public interface RzTalentService {
    Integer addTalent(@Param("rzTalent") RzTalent rzTalent);

    // 分页查询所有人才信息
    List<RzTalent> getAllRzTalentsByPage(@Param("start") int start, @Param("size") int size, String query, Long positionId);

    // 查询所有人才的总数
    int getRzTalentsCount(@Param("query") String query, @Param("positionId") Long positionId);

    // 根据 talentId 更新人才信息
    int updateRzTalent(RzTalent rzTalent);

    // 根据 talentId 删除人才
    int deleteRzTalent(Long talentId);

    // 根据 talentId 查询人才信息
    RzTalent getRzTalentById(Long talentId);

    // 查询所有人才信息
    List<RzTalent> getAllRzTalents();

    int addBatch(List<RzTalent> rzTalents);

    RzTalent getRzTalentInformation(Long talentId);

    List<Map<String, Object>> getAllTalentMap();

    List<Map<String, Object>> getTalentSort();
}
