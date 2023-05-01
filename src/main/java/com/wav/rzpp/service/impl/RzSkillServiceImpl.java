package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzSkill;
import com.wav.rzpp.entity.RzTalentSkill;
import com.wav.rzpp.mapper.RzSkillMapper;
import com.wav.rzpp.service.RzSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@Service
public class RzSkillServiceImpl implements RzSkillService {
    @Autowired
    private RzSkillMapper rzSkillMapper;

    @Override
    public int addSkill(RzSkill rzSkill) {
        rzSkill.setCreateTime(System.currentTimeMillis());
        return rzSkillMapper.addSkill(rzSkill);
    }

    @Override
    public RzSkill getSkillById(Long skillId) {
        return rzSkillMapper.getSkillById(skillId);
    }

    @Override
    public int updateSkill(RzSkill rzSkill) {
        return rzSkillMapper.updateSkill(rzSkill);
    }

    @Override
    public int deleteSkill(Long skillId) {
        return rzSkillMapper.deleteSkill(skillId);
    }

    @Override
    public List<RzSkill> getAllSkills() {
        return rzSkillMapper.getAllSkills();
    }

    @Override
    public int addSkills(List<RzSkill> rzSkills) {
        return rzSkillMapper.addSkills(rzSkills);
    }

    @Override
    public List<Map<String, Object>> getSkillListByTalentId(Long talentId) {
        return rzSkillMapper.getSkillListByTalentId(talentId);
    }

    @Override
    public List<RzTalentSkill> getAllTalentSkill() {
        return rzSkillMapper.getAllTalentSkill();
    }
}
