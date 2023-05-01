package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
public interface RzMessageService {
    List<Map<String, Object>> getHistoryMessage(@Param("senderId") Long senderId, @Param("receiveId") Long receiveId);

    String getLastMessage(@Param("senderId") Long senderId,@Param("receiveId") Long receiveId);

    Integer addMessage(@Param("rzMessage") RzMessage rzMessage);

    List<RzMessage> getAllMessage();
}
