package com.haven.businessService.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectFoodVo {
    @ApiModelProperty(value = "食品id")
    private String id;
    @ApiModelProperty(value = "食品名字")
    private String foodName;

    @ApiModelProperty(value = "食品描述")
    private String foodDescribe;

}
