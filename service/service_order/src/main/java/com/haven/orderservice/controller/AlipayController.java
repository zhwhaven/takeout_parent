package com.haven.orderservice.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.service.TOrderService;
import com.haven.orderservice.vo.AliPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    TOrderService orderService;
    @GetMapping("/pay")
    public String pay(AliPay aliPay) {
        System.out.println(aliPay.getTotalAmount());
        AlipayTradePagePayResponse response;
        try {
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(aliPay.getSubject(), aliPay.getTraceNo(), String.valueOf(aliPay.getTotalAmount()), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return response.getBody();
    }
    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }
            System.out.println("支付宝验签");
            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");

//            System.out.println("test:"+Factory.Payment.Common().verifyNotify(params));
            // 支付宝验签
//            if (Factory.Payment.Common().verifyNotify(params)) {
//                // 验签通过
//                System.out.println("交易名称: " + params.get("subject"));
//                System.out.println("交易状态: " + params.get("trade_status"));
//                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
//                System.out.println("商户订单号: " + params.get("out_trade_no"));
//                System.out.println("交易金额: " + params.get("total_amount"));
//                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
//                System.out.println("买家付款时间: " + params.get("gmt_payment"));
//                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
//
//                System.out.println("交易名称"+params.get("subject"));
//
//                // 更新订单未已支付
////                orderMapper.updateState(tradeNo, 1, gmtPayment);
//                String orderid = params.get("subject");
//
//                TOrder order = orderService.getById(orderid);
//                order.setOrderStatus(1);
//                orderService.updateById(order);
//            }
            String orderid = params.get("subject");

            orderService.pay(orderid);
//            order.setOrderStatus(1);
//            orderService.updateById(order);
        }
        return "success";
    }


}
