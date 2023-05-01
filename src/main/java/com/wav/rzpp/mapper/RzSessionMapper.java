package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzSession;
import com.wav.rzpp.service.RzSessionService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

/**
 * @author: hbw
 **/
@Mapper
public interface RzSessionMapper {
    /**
     * 查询用户的会话列表
     *
     * @param senderId
     * @return
     */
    List<Map<String, Object>> getSessionListBySenderId(@Param("senderId") Long senderId);


    Integer addSession(@Param("rzSession") RzSession rzSession);

    RzSession getSessionListBySenderIdAndReceiveId(@Param("senderId") Long senderId, @Param("receiveId") Long receiveId);
}
