package com.haven.utilscommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FoodVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "食品id")
    private String id;

    @ApiModelProperty(value = "食品名字")
    private String foodName;

    @ApiModelProperty(value = "食品图片")
    private String foodPicture;

    @ApiModelProperty(value = "食品描述")
    private String foodDescribe;

    @ApiModelProperty(value = "食品价格")
    private Double foodPrice;

    @ApiModelProperty(value = "真实价格")
    private Double realPrice;

    @ApiModelProperty(value = "食品折扣")
    private Double foodDiscount;

    @ApiModelProperty(value = "食品库存")
    private Integer foodInventory;

    @ApiModelProperty(value = "食品每日供应数量")
    private Integer foodSupply;

    @ApiModelProperty(value = "食品总销售数量")
    private Integer foodCount;

    @ApiModelProperty(value = "食品种类id")
    private String styleId;

    @ApiModelProperty(value = "食品种类父类id")
    private String parentStyleId;

    @ApiModelProperty(value = "份量")
    private String foodWeight;


    @ApiModelProperty(value = "商家id")
    private String businessId;

}
