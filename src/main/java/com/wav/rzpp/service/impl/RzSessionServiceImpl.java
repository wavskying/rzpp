package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzSession;
import com.wav.rzpp.mapper.RzSessionMapper;
import com.wav.rzpp.service.RzMessageService;
import com.wav.rzpp.service.RzSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@Service
public class RzSessionServiceImpl implements RzSessionService {

    @Autowired
    RzSessionMapper rzSessionMapper;

    @Autowired
    RzMessageService rzMessageService;

    @Override
    public List<Map<String, Object>> getSessionListBySenderId(Long senderId) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> sessionList = rzSessionMapper.getSessionListBySenderId(senderId);
        for (Map sessionMap : sessionList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sessionMap.get("receive_id").toString());
            map.put("name", sessionMap.get("name"));
            map.put("dept", rzMessageService.getLastMessage(senderId, Long.parseLong(sessionMap.get("receive_id").toString())));
            map.put("img", sessionMap.get("image"));
            map.put("readNum", sessionMap.get("un_read"));
            result.add(map);
        }
        return result;
    }

    @Override
    public Integer addSession(RzSession rzSession) {
        return rzSessionMapper.addSession(rzSession);
    }

    @Override
    public RzSession getSessionListBySenderIdAndReceiveId(Long senderId, Long receiveId) {
        return rzSessionMapper.getSessionListBySenderIdAndReceiveId(senderId, receiveId);
    }

    @Override
    public boolean isExist(Long senderId, Long receiveId) {
        return getSessionListBySenderIdAndReceiveId(senderId, receiveId) != null;
    }
}
