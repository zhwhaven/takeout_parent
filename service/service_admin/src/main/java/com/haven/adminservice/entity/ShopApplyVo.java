package com.haven.adminservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class ShopApplyVo {

    @ApiModelProperty(value = "商家id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个人身份证照片")
    private String idcardPicture;

    @ApiModelProperty(value = "营业执照图片")
    private String licensePicture;

    @ApiModelProperty(value = "申请描述")
    private String describe;

    @ApiModelProperty(value = "店铺名")
    private String storeName;

    @ApiModelProperty(value = "店铺描述")
    private String storeDescribe;

    @ApiModelProperty(value = "店铺照片")
    private String storePicture;

    @ApiModelProperty(value = "店铺电话")
    private String storePhone;

    @ApiModelProperty(value = "店铺地址")
    private String storeAddress;

    @ApiModelProperty(value = "店铺开始时间")
    private Integer startTime;

    @ApiModelProperty(value = "店铺结束时间")
    private Integer overTime;

    @ApiModelProperty(value = "店铺出餐时间")
    private Integer pushTime;

}
