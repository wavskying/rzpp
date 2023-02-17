package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzPosition;
import com.wav.rzpp.mapper.RzPositionMapper;
import com.wav.rzpp.service.RzPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzPositionServiceImpl implements RzPositionService {

    @Autowired
    private RzPositionMapper rzPositionMapper;

    @Override
    public Integer addRzPosition(RzPosition rzPosition) {
        rzPosition.setCreateTime(System.currentTimeMillis());
        return rzPositionMapper.addRzPosition(rzPosition);
    }

    @Override
    public Integer addRzPositionList(List<RzPosition> rzPositionList) {
        return rzPositionMapper.addRzPositionList(rzPositionList);
    }
}
