package com.haven.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.service.TOrderService;
import com.haven.orderservice.vo.OrderAndShopcarVo;
import com.haven.orderservice.vo.OrderAndSonOrderVo;
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

    @ApiOperation("支付订单")
    @PostMapping("/payforOrder")
    public R payforOrder(
            @RequestBody(required = false) OrderAndSonOrderVo orderAndShopcarVo){
        TOrder order = orderAndShopcarVo.getOrder();
        System.out.println("order.getId() = " + order.getId());
        String payUrl = "http://localhost:8007/alipay/pay?subject=" + order.getId() + "&traceNo=" + order.getId() + "&totalAmount=" +order.getAllPrice();
        return R.ok().data("payUrl",payUrl);
    }
    @ApiOperation("用户查询所有订单")
    @GetMapping("/selectAllOrder/{id}")
    public R selectAllOrder(@PathVariable String id){
        List<OrderAndSonOrderVo>  orderList =orderService.selectAllOrder(id);
        return R.ok().data("orderList",orderList);
    }
    @ApiOperation("根据状态查询订单")
    @GetMapping("/selectByStatus/{id}/{status}")
    public R selectByStatus(@PathVariable String id,@PathVariable Integer status){

        List<OrderAndSonOrderVo>  orderList =orderService.selectByStatus(id,status);
        return R.ok().data("orderList",orderList);
    }

    @ApiOperation("用户端修改订单状态")
    @PostMapping("/updateStatus/{id}/{status}/{orderid}")
    public R updateStatus(@PathVariable String id,@PathVariable Integer status,@PathVariable String orderid){
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id", id);
        TOrder order = orderService.getOne(wrapper);
        order.setOrderStatus(status);
        boolean b = orderService.updateById(order);
        if(b){
            return R.ok();
        }else
            return R.error();
    }

    @ApiOperation("商家查询所有订单")
    @GetMapping("/selectAllOrderByBsid/{bsid}")
    public R selectAllOrderByBsid(@PathVariable String bsid){
        List<OrderAndSonOrderVo>  orderList =orderService.selectAllOrderByBsid(bsid);
        return R.ok().data("orderList",orderList);
    }

    @ApiOperation("骑手查询所有订单")
    @GetMapping("/selectAllOrderByCid/{cid}")
    public R selectAllOrderByCid(@PathVariable String cid){
        List<OrderAndSonOrderVo>  orderList =orderService.selectAllOrderByCid(cid);
        return R.ok().data("orderList",orderList);
    }

    @ApiOperation("无条件查询所有订单")
    @GetMapping("/selectAllOrderByNull")
    public R selectAllOrderByNull(){
        List<OrderAndSonOrderVo>  orderList =orderService.selectAllOrderByNull();
        return R.ok().data("orderList",orderList);
    }



    //    各个端口都可以使用的： 根据订单id和传入的状态码修改状态
    @ApiOperation("各个端口修改订单状态")
    @PostMapping("/updateAllStatus/{id}/{status}")
    public R updateAllStatus(@PathVariable String id,@PathVariable Integer status){
        TOrder order = orderService.getById(id);
        order.setOrderStatus(status);
        boolean b = orderService.updateById(order);
        if(b)
            return R.ok();
        else
            return R.error();
    }

    @ApiOperation("商家发货改变订单的状态，改变订单的开始时间，结束时间")
    @PostMapping("/businessDeliverFood/{id}")
    public R businessDeliverFood(@PathVariable String id) {
        Boolean b=orderService.businessDeliverFood(id);
        if(b)
            return R.ok();
        else
            return R.error();
    }

    @ApiOperation("骑手接单，添加骑手id，添加骑手的开始时间")
    @PostMapping("/courierDeliverFood/{id}/{cid}")
    public R courierDeliverFood(@PathVariable String id,@PathVariable String cid) {
        Boolean b=orderService.courierDeliverFood(id,cid);
        if(b)
            return R.ok();
        else
            return R.error();
    }

    @ApiOperation("骑手完成订单，添加骑手的到达时间时间")
    @PostMapping("/courierArrive/{id}/{cid}")
    public R courierArrive(@PathVariable String id,@PathVariable String cid) {
        Boolean b=orderService.courierArrive(id,cid);
        if(b)
            return R.ok();
        else
            return R.error();
    }

}

