package com.haven.utilscommon.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StoreVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家id")
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
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
