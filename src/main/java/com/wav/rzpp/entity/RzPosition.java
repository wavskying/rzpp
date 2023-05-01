package com.wav.rzpp.entity;

import com.wav.rzpp.group.Add;
import com.wav.rzpp.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: hbw
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzPosition {

    @NotNull(message = "positionId不能为空", groups = {Update.class, Delegate.class})
    private Long positionId;

    @NotBlank(message = "name不能为空", groups = {Add.class})
    private String name;

    private Long createTime;

    @NotNull(message = "pid不能为空", groups = {Add.class})
    private Long pid;

    private String description;

}
