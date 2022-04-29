package com.haven.adminservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectUserVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "昵称")
    private String nickname;
}
