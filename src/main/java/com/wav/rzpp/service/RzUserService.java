package com.wav.rzpp.service;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzRole;
import com.wav.rzpp.entity.RzUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
public interface RzUserService {

    /**
     * 通过多个动态参数查询单个RzUser对象
     *
     * @param rzUser
     * @return
     */
    public RzUser getRzUserByUserAnd(@Param("rzUser") RzUser rzUser);

    /**
     * 通过多个动态参数查询RzUser的集合
     *
     * @param rzUser
     * @return
     */
    public List<RzUser> getRzUserListByUserAnd(@Param("rzUser") RzUser rzUser);

    /**
     * 通过多个动态参数Or连接查询RzUser的集合
     *
     * @param rzUser
     * @return
     */
    public RzUser getRzUserByUserOr(@Param("rzUser") RzUser rzUser);

    /**
     * 判断是否登陆成功
     *
     * @param username
     * @param password
     * @return
     */
    RzUser isLogin(String username, String password);

    /**
     * 判断用户注册信息是否已经存在
     *
     * @param rzUser
     * @return
     */
    boolean isExist(RzUser rzUser);

    Integer register(RzUser rzUser);

    List<RzUser> getAllUser();

    Integer updateUser(RzUser rzUser);

    Integer deleteUser(long parseLong);

    void addRzUser(RzUser rzUser);

    RzUser getUserById(long parseLong);

    List<RzUser> searchUser(String text);

    RzUser adminIsLogin(String username, String password);
}
