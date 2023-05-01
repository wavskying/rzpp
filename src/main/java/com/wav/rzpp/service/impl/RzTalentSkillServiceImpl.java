package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzTalentSkill;
import com.wav.rzpp.mapper.RzSkillMapper;
import com.wav.rzpp.mapper.RzTalentSkillMapper;
import com.wav.rzpp.service.RzTalentSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzTalentSkillServiceImpl implements RzTalentSkillService {
    @Autowired
    RzSkillMapper rzSkillMapper;

    @Autowired
    private RzTalentSkillMapper rzTalentSkillMapper;

    @Override
    public int addRzTalentSkill(RzTalentSkill rzTalentSkill) {
        return rzTalentSkillMapper.addRzTalentSkill(rzTalentSkill);
    }

    @Override
    public int addRzTalentSkillList(List<RzTalentSkill> rzTalentSkillList) {
        return rzTalentSkillMapper.addRzTalentSkillList(rzTalentSkillList);
    }

    @Override
    public List<RzTalentSkill> getAllTalentSkill() {
        return rzSkillMapper.getAllTalentSkill();
    }

    @Override
    public void updateTalentSkill(String talentId, String skillId, String ability) {
        rzTalentSkillMapper.updateTalentSkill(talentId, skillId, ability);
    }
}
