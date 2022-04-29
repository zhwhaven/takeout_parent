package com.haven.orderservice.controller;


import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.service.TOrderService;
import com.haven.orderservice.vo.OrderAndShopcarVo;
import com.haven.utilscommon.vo.R;
import com.haven.utilscommon.vo.ShopcarVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Api(description = "用户订单管理")
@RestController
@RequestMapping("/orderservice/t-order")
public class TOrderController {

    @Autowired
    TOrderService orderService;
    @ApiOperation("生成订单")
    @PostMapping("/makeOrder")
    public R makeOrder(
                       @RequestBody(required = false) OrderAndShopcarVo orderAndShopcarVo){
        TOrder order = orderAndShopcarVo.getOrder();
        List<ShopcarVo> shopcarVoList = orderAndShopcarVo.getShopcarVoList();
        orderService.makeOrder(order,shopcarVoList);
        System.out.println("order.getId() = " + order.getId());
        String payUrl = "http://localhost:8007/alipay/pay?subject=" + order.getId() + "&traceNo=" + order.getId() + "&totalAmount=" +order.getAllPrice();
        return R.ok().data("payUrl",payUrl);
    }

}

