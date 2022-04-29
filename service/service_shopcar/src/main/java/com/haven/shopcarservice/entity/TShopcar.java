package com.haven.shopcarservice.entity;

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
 * 购物车表
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TShopcar对象", description="购物车表")
public class TShopcar implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
