package com.wav.rzpp.controller;

import com.alibaba.fastjson.JSONObject;
import com.wav.rzpp.annotation.ExcludeJwtVerify;
import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzRole;
import com.wav.rzpp.entity.RzUser;
import com.wav.rzpp.group.Add;
import com.wav.rzpp.service.RzRoleService;
import com.wav.rzpp.service.RzUserService;
import com.wav.rzpp.utils.JWTUtil;
import com.wav.rzpp.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.validator.ValidatorException;

import javax.validation.Valid;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("用户模块")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RzUserService rzUserService;

    @Autowired
    private RzRoleService rzRoleService;

    /**
     * 登陆认证接口，认证成功返回token给前端
     *
     * @param rzUser user对象存储username和password数据
     * @return 统一结果集
     * @throws ValidatorException 抛出参数验证异常
     */
    @ApiOperation("登陆接口")
    @ExcludeJwtVerify
    @PostMapping("/login")
    public AjaxResult login(RzUser rzUser) throws ValidatorException {
        ValidatorUtil.checkLogin(rzUser.getUsername(), rzUser.getPassword());
        RzUser result = rzUserService.isLogin(rzUser.getUsername(), rzUser.getPassword());
        if (result != null) {
            RzRole rzRole = new RzRole();
            rzRole.setRoleId(result.getRoleId());
            RzRole rzRoleByRole = rzRoleService.getRzRoleByRoleAnd(rzRole);
            result.setPassword(null);
            String token = JWTUtil.getToken(result, rzRoleByRole);
            result.setToken(token);
            return AjaxResult.success(result);
        }
        return AjaxResult.failed("用户名或密码错误");
    }

    /**
     * 用户注册接口
     *
     * @param rzUser 注册使用的用户实体类
     * @return 注册成功返回统一结果集
     */
    @ApiOperation("注册接口")
    @ExcludeJwtVerify
    @PostMapping("/register")
    public AjaxResult register(@Validated(Add.class) RzUser rzUser) {
        if (rzUserService.isExist(rzUser)) {
            return AjaxResult.failed("注册信息已存在");
        }
        if (rzUserService.register(rzUser) != 0)
            return AjaxResult.success("注册成功");
        return AjaxResult.failed("注册失败");
    }
}
