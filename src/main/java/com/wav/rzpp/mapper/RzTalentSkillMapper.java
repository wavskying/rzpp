package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzTalent;
import com.wav.rzpp.entity.RzTalentSkill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzTalentSkillMapper {
    /**
     * 单个添加
     *
     * @param rzTalentSkill
     * @return
     */
    Integer addRzTalentSkill(RzTalentSkill rzTalentSkill);

    /**
     * 批量添加
     *
     * @param rzTalentSkillList
     * @return
     */
    Integer addRzTalentSkillList(List<RzTalentSkill> rzTalentSkillList);

    void updateTalentSkill(String talentId, String skillId, String ability);

}
