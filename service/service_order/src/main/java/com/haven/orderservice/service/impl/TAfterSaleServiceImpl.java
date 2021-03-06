package com.haven.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.orderservice.client.*;
import com.haven.orderservice.entity.TAfterSale;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.entity.TSonOrder;
import com.haven.orderservice.mapper.TAfterSaleMapper;
import com.haven.orderservice.mapper.TOrderMapper;
import com.haven.orderservice.mapper.TSonOrderMapper;
import com.haven.orderservice.service.TAfterSaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.orderservice.service.TOrderService;
import com.haven.orderservice.service.TSonOrderService;
import com.haven.orderservice.vo.OrderAndSonOrderVo;
import com.haven.utilscommon.vo.CourierVo;
import com.haven.utilscommon.vo.MemberVo;
import com.haven.utilscommon.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 售后表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Service
public class TAfterSaleServiceImpl extends ServiceImpl<TAfterSaleMapper, TAfterSale> implements TAfterSaleService {

    @Autowired
    TOrderService orderService;
    @Autowired
    TSonOrderMapper sonOrderMapper;
    @Autowired
    FoodClient foodClient;
    @Autowired
    ShopcarClient shopcarClient;
    @Autowired
    StoreClient storeClient;
    @Autowired
    CourierClient courierClient;
    @Autowired
    MemberClient memberClient;
    @Override
    public void addAfterSale(TAfterSale afterSale) {
//        添加售后
        int insert = baseMapper.insert(afterSale);
        if(insert!=1){
            throw new GuliException(20001,"添加售后失败");
        }
        String orderId = afterSale.getOrderId();
//        设置状态码为6，售后申请中
        Integer status=7;
        Boolean a=orderService.updateStatus(orderId,status);
        if(a==false){
            throw new GuliException(20001,"售后申请失败");
        }
    }
//查询订单的所有信息
    @Override
    public OrderAndSonOrderVo selectOrderMessageById(String id) {
        OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();

        TAfterSale tAfterSale = baseMapper.selectById(id);
        TOrder order = orderService.getById(tAfterSale.getOrderId());
        orderAndSonOrderVo.setOrder(order);

        List<TSonOrder> sonOrderList = sonOrderMapper.selectList(new QueryWrapper<TSonOrder>().eq("order_id", order.getId()));
        orderAndSonOrderVo.setSonOrderList(sonOrderList);

        String memberId = order.getMemberId();
        MemberVo memberVo = memberClient.selectById(memberId);
        orderAndSonOrderVo.setMemberVo(memberVo);

        String businessId = order.getBusinessId();
        StoreVo storeVo = storeClient.selectStoreById(businessId);
        orderAndSonOrderVo.setStoreVo(storeVo);

        String curierId = order.getCurierId();
        CourierVo courierVo = courierClient.selectById(curierId);
        orderAndSonOrderVo.setCourierVo(courierVo);

        return orderAndSonOrderVo;
    }
//同意退款,商家全责
    @Override
    public Boolean agreeById(String id) {
        TAfterSale tAfterSale = baseMapper.selectById(id);
        if(tAfterSale!=null)
        tAfterSale.setApplyStatus(1);
        int i = baseMapper.updateById(tAfterSale);
        if(i==1){
            String orderId = tAfterSale.getOrderId();
            TOrder order = orderService.getById(orderId);
            if(order!=null)
            order.setOrderStatus(9);
            boolean b = orderService.updateById(order);
            System.out.println("修改订单状态完: "+b);
            if(b==true){
                System.out.println("进入到商家添加坏单数界面");
//                增加商家的坏单数
                Boolean aBoolean = storeClient.addBadNumber(order.getBusinessId());
                System.out.println("添加坏单数量是否成功："+aBoolean);
                return aBoolean;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
//拒绝退款
    @Override
    public Boolean refuseById(String id) {
        TAfterSale tAfterSale = baseMapper.selectById(id);
        if(tAfterSale!=null)
        tAfterSale.setApplyStatus(2);
        int i = baseMapper.updateById(tAfterSale);
        if(i==1){
            String orderId = tAfterSale.getOrderId();
            TOrder order = orderService.getById(orderId);
            if(order!=null)
            order.setOrderStatus(8);
            System.out.println("进入修改订单");
            boolean b = orderService.updateById(order);
            System.out.println("是否修改订单成功："+b);
            return b;
        }else{
            return false;
        }
    }
    //同意退款,骑手全责
    @Override
    public Boolean agreeByCourierId(String id) {
        TAfterSale tAfterSale = baseMapper.selectById(id);
        if(tAfterSale!=null)
            tAfterSale.setApplyStatus(1);
        int i = baseMapper.updateById(tAfterSale);
        if(i==1){
            String orderId = tAfterSale.getOrderId();
            TOrder order = orderService.getById(orderId);
            if(order!=null)
                order.setOrderStatus(11);
            boolean b = orderService.updateById(order);
            if(b){
//                增加骑手的坏单数
                Boolean aBoolean = courierClient.addBadNumber(order.getCurierId());
                return aBoolean;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
}
