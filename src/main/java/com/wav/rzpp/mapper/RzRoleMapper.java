package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzRoleMapper {
    /**
     * 通过多个动态参数查询单个RzRole对象
     *
     * @param rzRole 实体类内部是动态参数
     * @return 单个实体类
     */
    public RzRole getRzRoleByRoleAnd(@Param("rzRole") RzRole rzRole);

    /**
     * 通过多个动态参数查询RzRole集合
     *
     * @param rzRole 实体类内部是动态参数
     * @return 集合
     */
    public List<RzRole> getRzRoleListByRoleAnd(@Param("rzRole") RzRole rzRole);
}
