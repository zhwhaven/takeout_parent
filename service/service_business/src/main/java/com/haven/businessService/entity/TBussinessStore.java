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
 * 商家店铺表
 * </p>
 *
 * @author testjava
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBussinessStore对象", description="商家店铺表")
public class TBussinessStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家id")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "店铺名")
    private String storeName;

    @ApiModelProperty(value = "店铺描述")
    private String storeDescribe;

    @ApiModelProperty(value = "店铺照片")
    private String storePicture;

    @ApiModelProperty(value = "店铺电话")
    private String storePhone;

    @ApiModelProperty(value = "销售总数")
    private int  foodCount;

    @ApiModelProperty(value = "店铺地址")
    private String storeAddress;

    @ApiModelProperty(value = "店铺开始时间")
    private String startTime;

    @ApiModelProperty(value = "店铺结束时间")
    private String overTime;

    @ApiModelProperty(value = "店铺出餐时间")
    private Integer pushTime;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

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
