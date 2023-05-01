package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.common.WebSocket;
import com.wav.rzpp.entity.RzMessage;
import com.wav.rzpp.entity.RzSession;
import com.wav.rzpp.service.RzMessageService;
import com.wav.rzpp.service.RzSessionService;
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
@Api("沟通模块")
@Validated
@RestController
@RequestMapping("/Communicate")
public class CommunicateController {

    @Autowired
    RzSessionService rzSessionService;

    @Autowired
    RzMessageService rzMessageService;

    @ApiOperation("获取所有通信数据")
    @PostMapping("/getAllMessage")
    public AjaxResult getAllMessage() {
        List<RzMessage> messageList = rzMessageService.getAllMessage();
        return AjaxResult.success(messageList);
    }

    @ApiOperation("查询用户的沟通列表")
    @GetMapping("/getSessionListBySenderId")
    public AjaxResult getSessionListBySenderId(@RequestParam("senderId") String senderId) {
        List<Map<String, Object>> sessionList = rzSessionService.getSessionListBySenderId(Long.parseLong(senderId));
        return AjaxResult.success(sessionList);
    }

    @ApiOperation("查询历史聊天记录")
    @GetMapping("/getHistoryMessage")
    public AjaxResult getHistoryMessage(@RequestParam("senderId") String senderId, @RequestParam("receiveId") String receiveId) {
        List<Map<String, Object>> historyMessage = rzMessageService.getHistoryMessage(Long.parseLong(senderId), Long.parseLong(receiveId));
        return AjaxResult.success(historyMessage);
    }

    @ApiOperation("添加沟通信息")
    @GetMapping("/addMessage")
    public AjaxResult addMessage(@RequestParam("senderId") String senderId, @RequestParam("receiveId") String receiveId, @RequestParam("content") String content, @RequestParam("createTime") String createTime) {
        RzMessage rzMessage = new RzMessage(SnowFlake.nextId(), Long.parseLong(senderId), Long.parseLong(receiveId), content, Long.parseLong(createTime));
        if (rzMessageService.addMessage(rzMessage) != 0) {
            if (WebSocket.sessionMap.containsKey(receiveId)) {
                WebSocket.sendMessage(senderId, WebSocket.sessionMap.get(receiveId));
            }
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.success("添加失败");
    }

    @ApiOperation("新建会话")
    @GetMapping("/addSession")
    public AjaxResult addSession(@RequestParam("senderId") String senderId, @RequestParam("receiveId") String receiveId) {
        long sid = Long.parseLong(senderId);
        long rid = Long.parseLong(receiveId);
        if (!rzSessionService.isExist(sid, rid) && !receiveId.equals("")) {
            rzSessionService.addSession(new RzSession(SnowFlake.nextId(), sid, rid, "你好,我想和您谈一谈!", 0, System.currentTimeMillis()));
            rzSessionService.addSession(new RzSession(SnowFlake.nextId(), rid, sid, "你好,我想和您谈一谈!", 1, System.currentTimeMillis()));
            rzMessageService.addMessage(new RzMessage(SnowFlake.nextId(), sid, rid, "你好,我想和您谈一谈!", System.currentTimeMillis()));
        }
        return AjaxResult.success("新建会话成功");
    }
}
