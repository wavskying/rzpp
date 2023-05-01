package com.wav.rzpp.service.impl;

import com.wav.rzpp.common.AjaxResult;
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
        if (rzUserByUser == null)
            return null;
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

    @Override
    public List<RzUser> getAllUser() {
        return rzUserMapper.getAllUser();
    }

    @Override
    public Integer updateUser(RzUser rzUser) {
        return rzUserMapper.updateUser(rzUser);
    }

    @Override
    public Integer deleteUser(long parseLong) {
        return rzUserMapper.deleteUser(parseLong);
    }

    @Override
    public void addRzUser(RzUser rzUser) {
        rzUserMapper.addRzUser(rzUser);
    }

    @Override
    public RzUser getUserById(long parseLong) {
        return rzUserMapper.getUserById(parseLong);
    }

    @Override
    public List<RzUser> searchUser(String text) {
        return rzUserMapper.searchUser(text);
    }

    @Override
    public RzUser adminIsLogin(String username, String password) {
        RzUser rzUser = new RzUser();
        rzUser.setUsername(username);
        RzUser rzUserByUser = rzUserMapper.getRzUserByUserAnd(rzUser);
        if (rzUserByUser == null)
            return null;
        if (rzUserByUser.getRoleId().equals(1L))
            return null;
        boolean match = DataSecurityUtil.isMatch(password, rzUserByUser.getPassword());
        if (match)
            return rzUserByUser;
        return null;
    }
}
