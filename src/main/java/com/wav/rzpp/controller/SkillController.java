package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzSkill;
import com.wav.rzpp.entity.RzTalentSkill;
import com.wav.rzpp.group.Add;
import com.wav.rzpp.group.Update;
import com.wav.rzpp.service.RzSkillService;
import com.wav.rzpp.service.RzTalentSkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("技能模块")
@Validated
@RestController
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private RzSkillService rzSkillService;

    @Autowired
    private RzTalentSkillService rzTalentSkillService;

    @ApiOperation("添加技能")
    @PostMapping("/add")
    public AjaxResult addRzSkill(@Validated(Add.class) RzSkill rzSkill) {
        int result = rzSkillService.addSkill(rzSkill);
        if (result != 0) {
            return AjaxResult.success("添加技能成功");
        }
        return AjaxResult.failed("添加技能失败");
    }


    @ApiOperation("更新技能信息")
    @PostMapping("/update")
    public AjaxResult updateRzSkill(@Validated(Update.class) RzSkill rzSkill) {
        int result = rzSkillService.updateSkill(rzSkill);
        if (result != 0) {
            return AjaxResult.success("更新技能信息成功");
        }
        return AjaxResult.failed("更新技能信息失败");
    }

    @ApiOperation("删除技能信息")
    @PostMapping("/delete")
    public AjaxResult deleteRzSkill(@RequestParam("skillId") Long skillId) {
        int result = rzSkillService.deleteSkill(skillId);
        if (result != 0) {
            return AjaxResult.success("删除技能信息成功");
        }
        return AjaxResult.failed("删除技能信息失败");
    }

    @ApiOperation("根据 skillId 查询技能信息")
    @GetMapping("/getById")
    public AjaxResult getRzSkillById(@RequestParam("skillId") Long skillId) {
        RzSkill rzSkill = rzSkillService.getSkillById(skillId);
        if (rzSkill != null) {
            return AjaxResult.success(rzSkill);
        }
        return AjaxResult.failed("查询技能信息失败");
    }

    @ApiOperation("查询所有技能信息")
    @GetMapping("/getAll")
    public AjaxResult getAllRzSkills() {
        List<RzSkill> rzSkills = rzSkillService.getAllSkills();
        if (!rzSkills.isEmpty()) {
            return AjaxResult.success(rzSkills);
        }
        return AjaxResult.failed("查询所有技能信息失败");
    }

    @ApiOperation("模拟关系表数据")
    @GetMapping("/generate")
    public AjaxResult addBatch2() {
        List<RzTalentSkill> rzTalentSkills = new ArrayList<>();

        Random rand = new Random();

        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            RzTalentSkill rzTalentSkill = new RzTalentSkill();
            Long skillId = (long) (rand.nextInt(28) + 1);
            Long talentId = getRandomTalentId();
            String test = skillId.toString() + talentId.toString();
            if (!hashSet.contains(test)) {
                hashSet.add(test);
            } else {
                continue;
            }
            rzTalentSkill.setSkillId(skillId);
            rzTalentSkill.setAbility((long) (rand.nextInt(51) + 50));
            rzTalentSkill.setTalentId(talentId);
            rzTalentSkills.add(rzTalentSkill);
        }

        rzTalentSkillService.addRzTalentSkillList(rzTalentSkills);
        return null;
    }

    private Long getRandomTalentId() {
        Long[] talentIds = {573657133367689216L, 573664021299855360L, 573664021299855361L,
                573664021299855362L, 573664021299855363L, 573664021299855364L, 573664021299855365L,
                573664021299855366L, 573664021299855367L, 573664021299855368L, 573664021299855369L,
                573664021299855370L, 573664021299855371L, 573664021299855372L, 573664021299855373L,
                573664021299855374L, 573664021299855375L, 573664021299855376L, 573664021299855377L,
                573664021299855378L, 573664021299855379L, 573664021299855380L, 573664021299855381L,
                573664021299855382L, 573664021299855383L, 573664021299855384L, 573664021299855385L,
                573664021299855386L, 573664021299855387L, 573664021299855388L, 573664021299855389L,
                573664021299855390L, 573664021299855391L, 573664021299855392L, 573664021299855393L,
                573664021299855394L, 573664021299855395L, 573664021299855396L, 573664021299855397L,
                573664021299855398L, 573664021299855399L, 573664021299855400L, 573664021299855401L,
                573664021299855402L, 573664021299855403L, 573664021299855404L, 573664021299855405L,
                573664021299855406L, 573664021299855407L, 573664021299855408L, 573664021299855409L,
                573664021299855410L, 573664021299855411L};
        // 创建一个随机数生成器
        Random random = new Random();

        // 从数组中随机选择一个元素
        Long randomTalentId = talentIds[random.nextInt(talentIds.length)];
        return randomTalentId;
    }

    @ApiOperation("插入100条技能数据")
    @PostMapping("/addBatch")
    public AjaxResult addBatch() {
        List<RzSkill> rzSkills = new ArrayList<>();
        rzSkills.add(new RzSkill(1L, "Java", "编程语言", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(2L, "Python", "编程语言", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(3L, "C++", "编程语言", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(4L, "Javascript", "编程语言", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(5L, "HTML/CSS", "前端技术", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(6L, "React", "前端框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(7L, "Vue", "前端框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(8L, "Spring Boot", "后端框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(9L, "Spring Cloud", "微服务框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(10L, "MyBatis", "ORM框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(11L, "Hibernate", "ORM框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(12L, "JPA", "ORM框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(13L, "Redis", "缓存数据库", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(14L, "MySQL", "关系型数据库", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(15L, "Oracle", "关系型数据库", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(16L, "MongoDB", "文档型数据库", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(17L, "Elasticsearch", "搜索引擎", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(18L, "RabbitMQ", "消息中间件", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(19L, "Kafka", "消息中间件", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(20L, "Docker", "容器技术", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(21L, "Kubernetes", "容器编排技术", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(22L, "Linux", "操作系统", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(23L, "Git", "版本控制工具", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(24L, "Maven", "项目构建工具", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(25L, "Gradle", "项目构建工具", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(26L, "JUnit", "单元测试框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(27L, "Mockito", "Mock框架", System.currentTimeMillis()));
        rzSkills.add(new RzSkill(28L, "Swagger", "API文档生成工具", System.currentTimeMillis()));
        rzSkillService.addSkills(rzSkills);
        return null;
    }
}
