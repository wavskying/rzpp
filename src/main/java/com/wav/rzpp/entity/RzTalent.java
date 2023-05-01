package com.wav.rzpp.entity;

import com.wav.rzpp.group.Add;
import com.wav.rzpp.group.Delete;
import com.wav.rzpp.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: hbw
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RzTalent {

    @NotNull(message = "userId不能为空", groups = {Update.class, Delete.class})
    private Long talentId;

    private String id;

    @NotBlank(message = "name不能为空", groups = {Add.class})
    private String name;

    private String sex;
    private Integer age;
    private String information;

    private String position;

    private String image;

    private String cost;

    private Long skillId;

    @NotNull(message = "positionId不能为空", groups = {Add.class})
    private Long positionId;

    @NotNull(message = "userId不能为空", groups = {Add.class})
    private Long userId;

    private String idTwo;

    private String positionName;
    private Long createTime;

    private String state;

    public void setTalentId(Long talentId) {
        this.talentId = talentId;
        this.id = talentId.toString();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        this.idTwo = userId.toString();
    }

    public void setPositionName(String positionName) {
        this.position = positionName;
        this.positionName = positionName;
    }

}
