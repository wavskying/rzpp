package com.wav.rzpp.entity;

import com.wav.rzpp.group.Add;
import com.wav.rzpp.group.Delete;
import com.wav.rzpp.group.Update;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

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

    @NotBlank(message = "username不能为空", groups = {Add.class})
    private String username;

    @NotBlank(message = "password不能为空", groups = {Add.class})
    private String password;

    @Email(message = "email必须是正确格式", groups = {Add.class, Update.class})
    private String email;

    private Long enterpriseId;

    @NotNull(message = "roleId不能为空")
    private Long roleId;

    private Long createTime;

    private String token;
}
