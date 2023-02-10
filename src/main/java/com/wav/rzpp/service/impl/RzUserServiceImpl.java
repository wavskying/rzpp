package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzUser;
import com.wav.rzpp.mapper.RzUserMapper;
import com.wav.rzpp.service.RzUserService;
import com.wav.rzpp.utils.DataSecurityUtil;
import com.wav.rzpp.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzUserServiceImpl implements RzUserService {
    @Autowired
    private RzUserMapper rzUserMapper;

    @Override
    public RzUser getRzUserByUserAnd(RzUser rzUser) {
        return rzUserMapper.getRzUserByUserAnd(rzUser);
    }

    @Override
    public List<RzUser> getRzUserListByUserAnd(RzUser rzUser) {
        return rzUserMapper.getRzUserListByUserAnd(rzUser);
    }

    @Override
    public RzUser getRzUserByUserOr(RzUser rzUser) {
        return rzUserMapper.getRzUserByUserOr(rzUser);
    }

    @Override
    public RzUser isLogin(String username, String password) {
        RzUser rzUser = new RzUser();
        rzUser.setUsername(username);
        RzUser rzUserByUser = rzUserMapper.getRzUserByUserAnd(rzUser);
        boolean match = DataSecurityUtil.isMatch(password, rzUserByUser.getPassword());
        if (match)
            return rzUserByUser;
        return null;
    }

    @Override
    public boolean isExist(RzUser rzUser) {
        RzUser rzUserByUserOr = rzUserMapper.getRzUserByUserOr(rzUser);
        return rzUserByUserOr != null;
    }

    @Override
    public Integer register(RzUser rzUser) {
        long id = SnowFlake.nextId();
        rzUser.setUserId(id);
        rzUser.setCreateTime(System.currentTimeMillis());
        rzUser.setPassword(DataSecurityUtil.encode(rzUser.getPassword()));
        return rzUserMapper.addRzUser(rzUser);
    }
}
