package com.haven.businessService.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 食品表
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TFood对象", description="食品表")
public class TFood implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "食品id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}
