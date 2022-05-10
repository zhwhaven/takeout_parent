package com.haven.orderservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SelectAfterSaleVo implements Serializable {

    private String id;
    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "商家id")
    private String businessId;

    @ApiModelProperty(value = "配送员id")
    private String curierId;
}
