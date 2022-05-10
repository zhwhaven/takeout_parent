package com.haven.orderservice.entity;

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
 * 订单表
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TOrder对象", description="订单表")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "送达的地址")
    private String address;

    @ApiModelProperty(value = "收件人")
    private String receivePerson;

    @ApiModelProperty(value = "配送时间")
    private String startTime;

    @ApiModelProperty(value = "预计送达时间")
    private String arriveTime;

    @ApiModelProperty(value = "收件人手机号码")
    private String receivePhone;

    @ApiModelProperty(value = "购买总数")
    private Integer allCount;

    @ApiModelProperty(value = "商家配送时间")
    private Integer pushTime;

    @ApiModelProperty(value = "购买总的价格")
    private Double allPrice;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "商家id")
    private String businessId;

    @ApiModelProperty(value = "状态码")
    private Integer orderStatus;

    @ApiModelProperty(value = "评分")
    private Integer score;

    @ApiModelProperty(value = "配送员id")
    private String curierId;

    @ApiModelProperty(value = "配送员接单开始")
    private String cstartTime;

    @ApiModelProperty(value = "配送员送达")
    private String cendTime;

    @ApiModelProperty(value = "店铺的地址")
    private String storeAddress;

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
