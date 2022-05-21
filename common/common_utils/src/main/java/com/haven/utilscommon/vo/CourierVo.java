package com.haven.utilscommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CourierVo {


    @ApiModelProperty(value = "快递员id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个人身份证照片")
    private String idcardPicture;

    @ApiModelProperty(value = "健康证明图片")
    private String healthyPicture;

    @ApiModelProperty(value = "申请描述")
    private String applyDescribe;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    private Integer age;

    private String idcardNumber;

    @ApiModelProperty(value = "总订单数")
    private Integer orderNumber;

    @ApiModelProperty(value = "坏单数")
    private Integer badNumber;

    @ApiModelProperty(value = "申请成功为1，否则为0")
    private Boolean isApply;
}
