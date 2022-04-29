package com.haven.adminservice.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class BusinessAndStoreVo {
    @ApiModelProperty(value = "商家id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "身份证号码")
    private String idcardNumber;


    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个人身份证照片")
    private String idcardPicture;

    @ApiModelProperty(value = "营业执照图片")
    private String licensePicture;

    @ApiModelProperty(value = "申请描述")
    private String applyDescribe;

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
    private String startTime;

    @ApiModelProperty(value = "店铺结束时间")
    private String overTime;

    @ApiModelProperty(value = "店铺出餐时间")
    private Integer pushTime;


    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;
}
