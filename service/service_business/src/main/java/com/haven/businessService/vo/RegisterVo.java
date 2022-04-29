package com.haven.businessService.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVo {

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

    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "身份证号码")
    private String idcardNumber;
    @ApiModelProperty(value = "验证码")
    private String recode;
    @ApiModelProperty(value = "重复密码")
    private String checkPass;

}
