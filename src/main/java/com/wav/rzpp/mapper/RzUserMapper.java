package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzUserMapper {

    Integer addRzUser(@Param("rzUser") RzUser rzUser);

    /**
     * 通过多个动态参数and连接查询单个RzUser对象
     *
     * @param rzUser
     * @return
     */
    RzUser getRzUserByUserAnd(@Param("rzUser") RzUser rzUser);

    /**
     * 通过多个动态参数and连接查询RzUser的集合
     *
     * @param rzUser
     * @return
     */
    List<RzUser> getRzUserListByUserAnd(@Param("rzUser") RzUser rzUser);

    /**
     * 通过多个动态参数Or连接查询RzUser的集合
     *
     * @param rzUser
     * @return
     */
    RzUser getRzUserByUserOr(@Param("rzUser") RzUser rzUser);

    List<RzUser> getAllUser();

    Integer updateUser(@Param("rzUser") RzUser rzUser);

    Integer deleteUser(@Param("userId") long userId);

    RzUser getUserById(@Param("userId") long userId);

    List<RzUser> searchUser(@Param("text") String text);
}
