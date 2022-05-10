package com.haven.orderservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SelectCommentVo implements Serializable {
    private String id;
    private String orderId;

}
