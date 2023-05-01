package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.common.PageResult;
import com.wav.rzpp.entity.RzTalent;
import com.wav.rzpp.entity.RzTalentSkill;
import com.wav.rzpp.group.Add;
import com.wav.rzpp.service.RzSkillService;
import com.wav.rzpp.service.RzTalentService;
import com.wav.rzpp.service.RzTalentSkillService;
import com.wav.rzpp.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("人才模块")
@Validated
@RestController
@RequestMapping("/talent")
public class TalentController {
    @Autowired
    private RzTalentService rzTalentService;

    @Autowired
    private RzSkillService rzSkillService;

    @Autowired
    private RzTalentSkillService rzTalentSkillService;

    @ApiOperation("获取人才排行榜")
    @GetMapping("/getTalentSort")
    public AjaxResult getTalentSort() {
        List<Map<String, Object>> mapList = rzTalentService.getTalentSort();
        return AjaxResult.success(mapList);
    }

    @ApiOperation("添加人才")
    @PostMapping("/addTalent")
    public AjaxResult addTalent(@Validated(Add.class) RzTalent rzTalent) {
        int result = rzTalentService.addTalent(rzTalent);
        if (result != 0) {
            return AjaxResult.success(rzTalent);
        }
        return AjaxResult.failed("添加人才失败");
    }

    @ApiOperation("获取人才详细信息")
    @GetMapping("/getTalentInformation")
    public AjaxResult getTalentInformation(@RequestParam("talentId") String talentId) {
        RzTalent rzTalentInformation = rzTalentService.getRzTalentInformation(Long.parseLong(talentId));
        List<Map<String, Object>> rzSkillList = rzSkillService.getSkillListByTalentId(Long.parseLong(talentId));
        return AjaxResult.success().add("information", rzTalentInformation).add("skillList", rzSkillList);
    }

    @ApiOperation("分页查询所有人才信息")
    @GetMapping("/getAllByPage")
    public AjaxResult getAllRzTalentsByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam(value = "query", required = false) String query, @RequestParam(value = "positionId", required = false) String positionId) {
        int start = (pageNum - 1) * pageSize;
        List<RzTalent> rzTalents = rzTalentService.getAllRzTalentsByPage(start, pageSize, query, positionId == null ? null : Long.parseLong(positionId));
        int totalCount = rzTalentService.getRzTalentsCount(query, positionId == null ? null : Long.parseLong(positionId));
        return AjaxResult.success(new PageResult<>(rzTalents, totalCount, pageSize, pageNum));
    }

    @ApiOperation("更新人才信息")
    @PostMapping("/update")
    public AjaxResult updateRzTalent(RzTalent rzTalent) {
        int result = rzTalentService.updateRzTalent(rzTalent);
        if (result != 0) {
            return AjaxResult.success("更新人才信息成功");
        }
        return AjaxResult.failed("更新人才信息失败");
    }

    @ApiOperation("更新人才技能信息")
    @PostMapping("/updateTalentSkill")
    public AjaxResult updateTalentSkill(String talentId, String skillId, String ability) {
        rzTalentSkillService.updateTalentSkill(talentId, skillId, ability);
        return AjaxResult.success();
    }

    @ApiOperation("添加人才技能")
    @PostMapping("/addTalentSKill")
    public AjaxResult addTalentSKill(String talentId, String skillId, String ability) {
        RzTalentSkill rzTalentSkill = new RzTalentSkill();
        rzTalentSkill.setTalentId(Long.parseLong(talentId));
        rzTalentSkill.setSkillId(Long.parseLong(skillId));
        rzTalentSkill.setAbility(Long.parseLong(ability));
        rzTalentSkillService.addRzTalentSkill(rzTalentSkill);
        return AjaxResult.success();
    }

    @ApiOperation("删除人才信息")
    @PostMapping("/delete")
    public AjaxResult deleteRzTalent(@RequestParam("talentId") Long talentId) {
        int result = rzTalentService.deleteRzTalent(talentId);
        if (result != 0) {
            return AjaxResult.success("删除人才信息成功");
        }
        return AjaxResult.failed("删除人才信息失败");
    }

    @ApiOperation("根据 talentId 查询人才信息")
    @GetMapping("/getById")
    public AjaxResult getRzTalentById(@RequestParam("talentId") Long talentId) {
        RzTalent rzTalent = rzTalentService.getRzTalentById(talentId);
        if (rzTalent != null) {
            return AjaxResult.success(rzTalent);
        }
        return AjaxResult.failed("查询人才信息失败");
    }

    @ApiOperation("查询所有人才信息")
    @GetMapping("/getAll")
    public AjaxResult getAllRzTalents() {
        System.out.println(11);
        List<Map<String, Object>> mapList = rzTalentService.getAllTalentMap();
        return AjaxResult.success(mapList);
    }

    @PostMapping("/test")
    public AjaxResult addTestTalent() {
        List<RzTalent> talents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RzTalent talent = new RzTalent();
            talent.setTalentId(SnowFlake.nextId());
            talent.setName("测试人才" + i);
            talent.setSex(i % 2 == 0 ? "男" : "女");
            talent.setAge(20 + i);
            talent.setInformation("这是测试数据");
            talent.setImage("https://video.akailibrary.com/zsh/210129/01/29/005-programmer.jpg");
            talent.setSkillId(1L);
            talent.setPositionId(556690896813293568L);
            talent.setPositionName("后端开发");
            talent.setCreateTime(System.currentTimeMillis());
            talents.add(talent);
        }
        int result = rzTalentService.addBatch(talents);
        if (result != 0) {
            return AjaxResult.success("添加人才成功");
        }
        return AjaxResult.failed("添加人才失败");
    }
}
