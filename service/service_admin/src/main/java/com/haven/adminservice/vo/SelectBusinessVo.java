package com.haven.adminservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectBusinessVo {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "名字")
    private String realname;
}
