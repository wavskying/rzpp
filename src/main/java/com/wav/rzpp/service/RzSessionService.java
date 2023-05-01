package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzSession;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
public interface RzSessionService {
    List<Map<String, Object>> getSessionListBySenderId(@Param("senderId") Long senderId);

    Integer addSession(@Param("rzSession") RzSession rzSession);

    RzSession getSessionListBySenderIdAndReceiveId(@Param("senderId") Long senderId, @Param("receiveId") Long receiveId);

    boolean isExist(@Param("senderId") Long senderId, @Param("receiveId") Long receiveId);

}
