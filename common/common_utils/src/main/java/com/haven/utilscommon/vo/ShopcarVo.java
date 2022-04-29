package com.haven.utilscommon.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShopcarVo implements Serializable {

    @ApiModelProperty(value = "购物车id")
    private String id;

    @ApiModelProperty(value = "食品id")
    private String foodId;

    @ApiModelProperty(value = "食品名字")
    private String foodName;

    @ApiModelProperty(value = "食品图片")
    private String foodPicture;

    @ApiModelProperty(value = "购买数量")
    private Integer count;

    @ApiModelProperty(value = "购买价格")
    private Double singlePrice;

    @ApiModelProperty(value = "购买总的价格")
    private Double allPrice;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "商家id")
    private String businessId;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

}
