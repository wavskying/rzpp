package com.wav.rzpp.entity;

import com.wav.rzpp.group.Add;
import com.wav.rzpp.group.Delete;
import com.wav.rzpp.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: hbw
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzUser {

    @NotNull(message = "userId不能为空", groups = {Update.class, Delete.class})
    private Long userId;

    private String id;

    @NotBlank(message = "username不能为空", groups = {Add.class})
    private String username;

    @NotBlank(message = "password不能为空", groups = {Add.class})
    private String password;

    //    @NotBlank(message = "name不能为空", groups = {Add.class})
    private String name;

    @Email(message = "email必须是正确格式", groups = {Add.class, Update.class})
    private String email;

    private String image;

    private Long enterpriseId;

    @NotNull(message = "roleId不能为空")
    private Long roleId;

    private String roleName;

    private Long createTime;

    private String token;

    public void setUserId(Long userId) {
        this.userId = userId;
        this.id = userId.toString();
    }

    public void setRoleId(Long roleId) {
        if (roleId.equals(1L)) {
            this.roleName = "超级管理员";
        } else if (roleId.equals(2L)) {
            this.roleName = "管理员";
        } else if (roleId.equals(3L)) {
            this.roleName = "负责人";
        } else {
            this.roleName = "普通用户";
        }
        this.roleId = roleId;
    }
}
