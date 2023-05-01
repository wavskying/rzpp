package com.wav.rzpp.controller;

import com.wav.rzpp.annotation.ExcludeJwtVerify;
import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzRole;
import com.wav.rzpp.entity.RzUser;
import com.wav.rzpp.group.Add;
import com.wav.rzpp.service.RzRoleService;
import com.wav.rzpp.service.RzUserService;
import com.wav.rzpp.utils.JWTUtil;
import com.wav.rzpp.utils.SnowFlake;
import com.wav.rzpp.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.validator.ValidatorException;

import java.util.List;
import java.util.Map;

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

    @ApiOperation("获取所有用户数据")
    @ExcludeJwtVerify
    @PostMapping("/getAllUser")
    public AjaxResult getAllUser() {
        return AjaxResult.success(rzUserService.getAllUser());
    }

    @ApiOperation("修改用户数据")
    @ExcludeJwtVerify
    @PostMapping("/updateUser")
    public AjaxResult updateUser(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        RzUser rzUser = new RzUser();
        rzUser.setUserId(Long.parseLong((String) map.get("userId")));
        rzUser.setImage(map.get("image").toString());
        rzUser.setRoleId(Long.parseLong((String) map.get("roleId")));
        rzUser.setEmail((String) map.get("email"));
        rzUser.setName((String) map.get("name"));
        rzUserService.updateUser(rzUser);
        return AjaxResult.success("修改成功");
    }

    @ApiOperation("删除用户")
    @ExcludeJwtVerify
    @PostMapping("/deleteUser")
    public AjaxResult deleteUser(@RequestParam("userId") String userId) {
        rzUserService.deleteUser(Long.parseLong(userId));
        return AjaxResult.success("删除成功");
    }

    @ApiOperation("查找单个用户")
    @ExcludeJwtVerify
    @PostMapping("/getUserById")
    public AjaxResult getUserById(@RequestParam("userId") String userId) {
        RzUser rzUser = rzUserService.getUserById(Long.parseLong(userId));
        return AjaxResult.success(rzUser);
    }

    @ApiOperation("添加用户")
    @ExcludeJwtVerify
    @PostMapping("/addUser")
    public AjaxResult addUser(RzUser rzUser) {
        String userId = String.valueOf(SnowFlake.nextId());
        rzUser.setUserId(Long.parseLong(userId));
        rzUser.setCreateTime(System.currentTimeMillis());
        rzUserService.addRzUser(rzUser);
        return AjaxResult.success(rzUser);
    }

    @ApiOperation("搜索用户")
    @ExcludeJwtVerify
    @PostMapping("/searchUser")
    public AjaxResult searchUser(@RequestParam("text") String text) {
        List<RzUser> userList = rzUserService.searchUser(text);
        return AjaxResult.success(userList);
    }

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
     * 登陆认证接口，认证成功返回token给前端
     *
     * @param rzUser user对象存储username和password数据
     * @return 统一结果集
     * @throws ValidatorException 抛出参数验证异常
     */
    @ApiOperation("管理员登陆接口")
    @ExcludeJwtVerify
    @PostMapping("/adminLogin")
    public AjaxResult adminLogin(RzUser rzUser) throws ValidatorException {
        ValidatorUtil.checkLogin(rzUser.getUsername(), rzUser.getPassword());
        RzUser result = rzUserService.adminIsLogin(rzUser.getUsername(), rzUser.getPassword());
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
            return AjaxResult.failed("账号已存在");
        }
        if (rzUserService.register(rzUser) != 0)
            return AjaxResult.success("注册成功");
        return AjaxResult.failed("注册失败");
    }
}
