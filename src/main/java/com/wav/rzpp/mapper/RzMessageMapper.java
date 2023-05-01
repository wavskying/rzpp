package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@Mapper
public interface RzMessageMapper {
    List<Map> getHistoryMessage(@Param("senderId") Long senderId, @Param("receiveId") Long receiveId);

    String getLastMessage(@Param("senderId") Long senderId,@Param("receiveId") Long receiveId);

    Integer addMessage(@Param("rzMessage")RzMessage rzMessage);

    List<RzMessage> getAllMessage();
}
