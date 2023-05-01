package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzTalentSkill;

import java.util.List;

/**
 * @author: hbw
 **/
public interface RzTalentSkillService {
    /**
     * 单个添加
     *
     * @param rzTalentSkill
     * @return
     */
    int addRzTalentSkill(RzTalentSkill rzTalentSkill);

    /**
     * 批量添加
     *
     * @param rzTalentSkillList
     * @return
     */
    int addRzTalentSkillList(List<RzTalentSkill> rzTalentSkillList);

    List<RzTalentSkill> getAllTalentSkill();

    void updateTalentSkill(String talentId, String skillId, String ability);
}
