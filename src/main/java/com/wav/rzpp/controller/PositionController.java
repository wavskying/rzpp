package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzPosition;
import com.wav.rzpp.entity.RzPositionVO;
import com.wav.rzpp.service.RzPositionService;
import com.wav.rzpp.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("职位模块")
@Validated
@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private RzPositionService rzPositionService;

    /**
     * 获取rzPosition的树形结构数据
     *
     * @return 统一结果集
     */
    @ApiOperation("获取职位树")
    @GetMapping("/getRzPositionTree")
    public AjaxResult getRzPositionTree() {
        List<Map<String, Object>> positionVOList = rzPositionService.getAllRzPosition();
        return AjaxResult.success(positionVOList);
    }

    @ApiOperation("添加职位")
    @PostMapping("/addPosition")
    public AjaxResult addPosition(String pid, String name) {
        long id = SnowFlake.nextId();
        RzPosition rzPosition = new RzPosition();
        rzPosition.setPositionId(id);
        rzPosition.setName(name);
        rzPosition.setCreateTime(System.currentTimeMillis());
        rzPosition.setPid(Long.parseLong(pid));
        rzPositionService.addRzPosition(rzPosition);
        return AjaxResult.success(String.valueOf(id));
    }

    @ApiOperation("修改职位")
    @PostMapping("/updatePosition")
    public AjaxResult updatePosition(String id, String name) {
        rzPositionService.updatePosition(id, name);
        return AjaxResult.success();
    }

    @ApiOperation("删除职位")
    @PostMapping("/deletePosition")
    public AjaxResult deletePosition(String id) {
        rzPositionService.deletePosition(id);
        return AjaxResult.success();
    }
}
