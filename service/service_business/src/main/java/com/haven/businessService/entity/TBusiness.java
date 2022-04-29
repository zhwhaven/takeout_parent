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
 * 商家信息表
 * </p>
 *
 * @author testjava
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBusiness对象", description="商家信息表")
public class TBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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
    private String applyDescribe;


    private Integer age;

    private String idcardNumber;

    @ApiModelProperty(value = "申请成功为1，否则为0")
    private Boolean isApply;


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
