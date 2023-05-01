package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzSkill;
import com.wav.rzpp.entity.RzTalent;
import com.wav.rzpp.entity.RzTalentSkill;
import com.wav.rzpp.mapper.RzTalentMapper;
import com.wav.rzpp.service.RzPositionService;
import com.wav.rzpp.service.RzSkillService;
import com.wav.rzpp.service.RzTalentService;
import com.wav.rzpp.utils.SnowFlake;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@Service
public class RzTalentServiceImpl implements RzTalentService {
    @Autowired
    private RzTalentMapper rzTalentMapper;

    @Autowired
    private RzSkillService rzSkillService;

    @Override
    public Integer addTalent(RzTalent rzTalent) {
        long id = SnowFlake.nextId();
        rzTalent.setTalentId(id);
        rzTalent.setCreateTime(System.currentTimeMillis());
        return rzTalentMapper.addTalent(rzTalent);
    }

    @Override
    public List<RzTalent> getAllRzTalentsByPage(int start, int size, String query, Long positionId) {
        return rzTalentMapper.getAllRzTalentsByPage(start, size, query, positionId);
    }

    @Override
    public int getRzTalentsCount(@Param("query") String query, @Param("positionId") Long positionId) {
        return rzTalentMapper.getRzTalentsCount(query, positionId);
    }

    @Override
    public int updateRzTalent(RzTalent rzTalent) {
        return rzTalentMapper.updateRzTalent(rzTalent);
    }

    @Override
    public int deleteRzTalent(Long talentId) {
        return rzTalentMapper.deleteRzTalent(talentId);
    }

    @Override
    public RzTalent getRzTalentById(Long talentId) {
        return rzTalentMapper.getRzTalentById(talentId);
    }

    @Override
    public List<RzTalent> getAllRzTalents() {
        return rzTalentMapper.getAllRzTalents();
    }

    @Override
    public int addBatch(List<RzTalent> rzTalents) {
        return rzTalentMapper.addBatch(rzTalents);
    }

    @Override
    public RzTalent getRzTalentInformation(Long talentId) {
        return rzTalentMapper.getRzTalentInformation(talentId);
    }

    @Override
    public List<Map<String, Object>> getAllTalentMap() {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        List<RzTalentSkill> allTalentSkill = rzSkillService.getAllTalentSkill();
        List<RzTalent> allRzTalents = getAllRzTalents();
        for (RzTalent rzTalent : allRzTalents) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", rzTalent.getId());
            map.put("image", rzTalent.getImage());
            map.put("name", rzTalent.getName());
            map.put("sex", rzTalent.getSex());
            map.put("state", rzTalent.getState());
            map.put("age", rzTalent.getAge());
            map.put("cost", rzTalent.getCost());
            map.put("createTIme", rzTalent.getCreateTime());
            map.put("position", rzTalent.getPositionName());
            map.put("positionId", rzTalent.getPositionId().toString());
            ArrayList<Map<String, Object>> innerData = new ArrayList<>();
            for (RzTalentSkill rzTalentSkill : allTalentSkill) {
                if (rzTalentSkill.getTalentId().equals(rzTalent.getTalentId())) {
                    Map<String, Object> talentSkillMap = new HashMap<String, Object>();
                    talentSkillMap.put("talentId", rzTalent.getTalentId().toString());
                    talentSkillMap.put("skillId", rzTalentSkill.getSkillId().toString());
                    talentSkillMap.put("skillName", rzTalentSkill.getSkillName());
                    talentSkillMap.put("ability", rzTalentSkill.getAbility());
                    innerData.add(talentSkillMap);
                }
            }
            map.put("innerData", innerData);
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getTalentSort() {
        return rzTalentMapper.getTalentSort();
    }


}
