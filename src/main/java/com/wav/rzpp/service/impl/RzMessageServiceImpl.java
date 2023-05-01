package com.wav.rzpp.service.impl;

import com.sun.corba.se.spi.ior.ObjectKey;
import com.wav.rzpp.entity.RzMessage;
import com.wav.rzpp.mapper.RzMessageMapper;
import com.wav.rzpp.service.RzMessageService;
import com.wav.rzpp.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: hbw
 **/
@Service
public class RzMessageServiceImpl implements RzMessageService {

    @Autowired
    RzMessageMapper rzMessageMapper;

    @Override
    public List<Map<String, Object>> getHistoryMessage(Long senderId, Long receiveId) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map> historyMessage = rzMessageMapper.getHistoryMessage(senderId, receiveId);
        for (Map historyMap : historyMessage) {
            Map<String, Object> map = new HashMap<>();
            String date = TimeUtil.TimeStampToTime(Long.parseLong(historyMap.get("create_time").toString()));
            Map<String, Object> textMap = new HashMap<>();
            textMap.put("text", historyMap.get("content"));
            if (historyMap.get("sender_id").toString().equals(senderId.toString())) {
                map.put("mine", Boolean.TRUE);
            } else {
                map.put("mine", Boolean.FALSE);
            }
            map.put("date", date);
            map.put("name", historyMap.get("name"));
            map.put("text", textMap);
            map.put("img", historyMap.get("image"));
            result.add(map);
        }
        return result;
    }

    @Override
    public String getLastMessage(Long senderId, Long receiveId) {
        return rzMessageMapper.getLastMessage(senderId, receiveId);
    }

    @Override
    public Integer addMessage(RzMessage rzMessage) {
        return rzMessageMapper.addMessage(rzMessage);
    }

    @Override
    public List<RzMessage> getAllMessage() {
        return rzMessageMapper.getAllMessage();
    }
}
