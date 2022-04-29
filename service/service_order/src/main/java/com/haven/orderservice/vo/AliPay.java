package com.haven.orderservice.vo;

import lombok.Data;

@Data
public class AliPay {
    String subject;
    String traceNo;
    Double totalAmount;
     
}