package com.wav.rzpp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wav.rzpp.annotation.ExcludeJwtVerify;
import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzPosition;
import com.wav.rzpp.service.RzPositionService;
import com.wav.rzpp.utils.HttpUtil;
import com.wav.rzpp.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("爬虫模块")
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    private RzPositionService rzPositionService;

    private static final String BOSS_POSITION_URL = "http://www.zhipin.com/wapi/zpCommon/data/position.json";

    @GetMapping("/getBossPositionData")
    @ApiOperation("爬取职位接口")
    @ExcludeJwtVerify
    public AjaxResult getBossPositionData() throws IOException {
        // 存放解析结果
        List<RzPosition> rzPositionList = new ArrayList<>();
        // 获取请求结果
        String data = HttpUtil.sendGet(BOSS_POSITION_URL);
        JSONObject jsonObject = JSONObject.parseObject(data);

        // 开始解析JSON对象
        JSONArray jsonArray = jsonObject.getJSONArray("zpData");
        analysisTree(rzPositionList, jsonArray, 0l, 1);
        rzPositionService.addRzPositionList(rzPositionList);
        return AjaxResult.success("爬取完成");
    }

    // 递归解析三层JSONArray
    public void analysisTree(List<RzPosition> rzPositionList, JSONArray jsonArray, Long pid, int num) {
        if (num == 4)
            return;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            Long id = SnowFlake.nextId();
            RzPosition rzPosition = new RzPosition(id, name, System.currentTimeMillis(), pid, "");
            rzPositionList.add(rzPosition);
            analysisTree(rzPositionList, jsonObject.getJSONArray("subLevelModelList"), id, num + 1);
        }
    }
}
