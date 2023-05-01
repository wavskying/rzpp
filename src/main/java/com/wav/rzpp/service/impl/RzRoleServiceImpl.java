package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzRole;
import com.wav.rzpp.mapper.RzRoleMapper;
import com.wav.rzpp.service.RzRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzRoleServiceImpl implements RzRoleService {

    @Autowired
    private RzRoleMapper rzRoleMapper;

    @Override
    public RzRole getRzRoleByRoleAnd(RzRole rzRole) {
        return rzRoleMapper.getRzRoleByRoleAnd(rzRole);
    }

    @Override
    public List<RzRole> getRzRoleListByRoleAnd(RzRole rzRole) {
        return rzRoleMapper.getRzRoleListByRoleAnd(rzRole);
    }
}
