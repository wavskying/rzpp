package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzSkill;
import com.wav.rzpp.entity.RzTalentSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
public interface RzSkillService {
    /**
     * 添加技能
     * @param rzSkill 技能实体类
     * @return 添加的技能数
     */
    int addSkill(RzSkill rzSkill);

    /**
     * 根据技能id查询技能信息
     * @param skillId 技能id
     * @return 技能实体类
     */
    RzSkill getSkillById(Long skillId);

    /**
     * 更新技能信息
     * @param rzSkill 技能实体类
     * @return 更新的技能数
     */
    int updateSkill(RzSkill rzSkill);

    /**
     * 删除技能
     * @param skillId 技能id
     * @return 删除的技能数
     */
    int deleteSkill(Long skillId);

    /**
     * 获取所有技能信息
     * @return 技能实体类列表
     */
    List<RzSkill> getAllSkills();

    int addSkills(@Param("rzSkills") List<RzSkill> rzSkills);

    List<Map<String, Object>> getSkillListByTalentId(@Param("talentId") Long talentId);

    List<RzTalentSkill> getAllTalentSkill();
}
