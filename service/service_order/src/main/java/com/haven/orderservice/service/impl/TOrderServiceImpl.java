package com.haven.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.orderservice.client.*;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.entity.TSonOrder;
import com.haven.orderservice.mapper.TOrderMapper;
import com.haven.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.orderservice.service.TSonOrderService;
import com.haven.orderservice.util.TimeUtils;
import com.haven.orderservice.vo.OrderAndSonOrderVo;
import com.haven.utilscommon.vo.CourierVo;
import com.haven.utilscommon.vo.MemberVo;
import com.haven.utilscommon.vo.ShopcarVo;
import com.haven.utilscommon.vo.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    TSonOrderService sonOrderService;
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
    public void makeOrder(TOrder order, List<ShopcarVo> shopcarVoList) {
        System.out.println("订单数据"+order);
        System.out.println("购物车数据"+shopcarVoList);

//        生成订单
        if(order!=null){
            String startTime = order.getStartTime();
            Integer pushTime = order.getPushTime();
            if(startTime!=null&&pushTime!=null){
                String receiveTime = TimeUtils.getReceiveTime(startTime, pushTime);
                order.setArriveTime(receiveTime);
            }

            int insert = baseMapper.insert(order);
            if(insert==0){
                throw new GuliException(20001,"生成订单失败");
            }
//        生成子订单
            String orderId = order.getId();
            if(shopcarVoList!=null){
                for (ShopcarVo shopcarVo : shopcarVoList) {
                    String foodId = shopcarVo.getFoodId();
                    Integer count = shopcarVo.getCount();
                    String shopcarId = shopcarVo.getId();
                    TSonOrder sonOrder=new TSonOrder();
                    BeanUtils.copyProperties(shopcarVo,sonOrder);
                    sonOrder.setId(null);
                    sonOrder.setShopcarId(shopcarId);
                    sonOrder.setOrderId(orderId);
                    boolean save = sonOrderService.save(sonOrder);
                    if(save==false){
                        throw new GuliException(20001,"生成订单失败");
                    }
//            减去库存
//                    Boolean aBoolean = foodClient.deleteFoodInventory(foodId, count);
//                    if(aBoolean==false){
//                        throw new GuliException(20001,"减去库存失败");
//
//                    }
//            删除购物车
                    Boolean aBoolean1 = shopcarClient.deleteShopcar(shopcarId);
                    if(aBoolean1==false){
                        throw new GuliException(20001,"删除购物车失败");
                    }
            }

        }

        }
    }


//    支付后完成后完成的一些操作
    @Override
    public void pay(String orderid) {

        TOrder order = baseMapper.selectById(orderid);
        String orderId = order.getId();
       QueryWrapper<TSonOrder> wrapper=new QueryWrapper<>();
        List<TSonOrder> sonOrderList = sonOrderService.list(wrapper.eq("order_id", orderid));
        for (TSonOrder sonOrder : sonOrderList) {
            String foodId = sonOrder.getFoodId();
            Integer count = sonOrder.getCount();
            //        将商品库存减少，增加销量
            //        将商家的销量增加
                     Boolean aBoolean = foodClient.deleteFoodInventory(foodId, count);
                    if(aBoolean==false){
                        throw new GuliException(20001,"减去库存失败,增加销量失败");

                    }
        }

//        将订单状态改为已支付的1
        order.setOrderStatus(1);
        int i = baseMapper.updateById(order);
        if(i!=1){
            throw new GuliException(20001,"订单修改失败");

        }
    }
//查询用户所有订单
    @Override
    public List<OrderAndSonOrderVo> selectAllOrder(String id) {
        ArrayList<OrderAndSonOrderVo> orderList = new ArrayList<>();

//        查询订单信息
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",id);
        List<TOrder> orders = baseMapper.selectList(wrapper);
        for (TOrder tOrder : orders) {
//            TOrder tOrder = baseMapper.selectOne(wrapper);
            String orderId = tOrder.getId();
//        得到商家的信息
            String businessId = tOrder.getBusinessId();
            StoreVo storeVo = storeClient.selectStoreById(businessId);
//        得到配送者的信息
            String curierId = tOrder.getCurierId();
            CourierVo courierVo=new CourierVo();
            if(curierId!=null){
                courierVo = courierClient.selectById(curierId);
            }
//        查询子订单的信息
            QueryWrapper<TSonOrder> wrapper1=new QueryWrapper<>();
            wrapper1.eq("order_id",orderId);
            List<TSonOrder> orderSonlist = sonOrderService.list(wrapper1);
//        整合
            OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();
            orderAndSonOrderVo.setOrder(tOrder);
            orderAndSonOrderVo.setStoreVo(storeVo);
            orderAndSonOrderVo.setCourierVo(courierVo);
            orderAndSonOrderVo.setSonOrderList(orderSonlist);
            orderList.add(orderAndSonOrderVo);
        }

        return orderList;
    }
//查询用户的订单信息，分状态
    @Override
    public List<OrderAndSonOrderVo> selectByStatus(String id, Integer status) {
        ArrayList<OrderAndSonOrderVo> orderList = new ArrayList<>();
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",id);
        wrapper.eq("order_status",status);
        List<TOrder> tOrders = baseMapper.selectList(wrapper);
//        TOrder tOrder = baseMapper.selectOne(wrapper);
        for (TOrder tOrder : tOrders) {
            String orderId = tOrder.getId();
//        得到商家的信息
            String businessId = tOrder.getBusinessId();
            StoreVo storeVo = storeClient.selectStoreById(businessId);

            //        得到配送者的信息
            String curierId = tOrder.getCurierId();
            CourierVo courierVo=new CourierVo();
            if(curierId!=null){
                courierVo = courierClient.selectById(curierId);
            }
//        查询商家子订单的信息
            QueryWrapper<TSonOrder> wrapper1=new QueryWrapper<>();
            wrapper1.eq("order_id",orderId);
            List<TSonOrder> orderSonlist = sonOrderService.list(wrapper1);
//        整合
            OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();
            orderAndSonOrderVo.setOrder(tOrder);
            orderAndSonOrderVo.setStoreVo(storeVo);
            orderAndSonOrderVo.setCourierVo(courierVo);
            orderAndSonOrderVo.setSonOrderList(orderSonlist);
            orderList.add(orderAndSonOrderVo);
        }

        return orderList;
    }

    @Override
    public Boolean updateStatus(String orderId, Integer status) {
        TOrder tOrder = baseMapper.selectById(orderId);
        tOrder.setOrderStatus(status);
        int i = baseMapper.updateById(tOrder);
        if(i==1)
            return true;
            else
        return false;
    }
//商家查询所有订单
    @Override
    public List<OrderAndSonOrderVo> selectAllOrderByBsid(String bsid) {
        ArrayList<OrderAndSonOrderVo> orderList = new ArrayList<>();

//        查询订单信息
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("business_id",bsid);
        List<TOrder> orders = baseMapper.selectList(wrapper);
        for (TOrder tOrder : orders) {
//            TOrder tOrder = baseMapper.selectOne(wrapper);
            String orderId = tOrder.getId();
//        得到用户的信息
            String memberId = tOrder.getMemberId();
            MemberVo memberVo = memberClient.selectById(memberId);
//        得到配送者的信息
            String curierId = tOrder.getCurierId();
            CourierVo courierVo=new CourierVo();
            if(curierId!=null){
                courierVo = courierClient.selectById(curierId);
            }
//        查询子订单的信息
            QueryWrapper<TSonOrder> wrapper1=new QueryWrapper<>();
            wrapper1.eq("order_id",orderId);
            List<TSonOrder> orderSonlist = sonOrderService.list(wrapper1);
//        整合
            OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();
            orderAndSonOrderVo.setOrder(tOrder);
            orderAndSonOrderVo.setMemberVo(memberVo);
            orderAndSonOrderVo.setCourierVo(courierVo);
            orderAndSonOrderVo.setSonOrderList(orderSonlist);
            orderList.add(orderAndSonOrderVo);
        }

        return orderList;
    }
    //骑手查询关于自己的订单
    @Override
    public List<OrderAndSonOrderVo> selectAllOrderByCid(String cid) {
        ArrayList<OrderAndSonOrderVo> orderList = new ArrayList<>();

//        查询订单信息
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("curier_id",cid);
        List<TOrder> orders = baseMapper.selectList(wrapper);
        for (TOrder tOrder : orders) {
//            TOrder tOrder = baseMapper.selectOne(wrapper);
            String orderId = tOrder.getId();
//        得到用户的信息
            String memberId = tOrder.getMemberId();
            MemberVo memberVo = memberClient.selectById(memberId);
//        得到商家的信息
            //        得到商家的信息
            String businessId = tOrder.getBusinessId();
            StoreVo storeVo = storeClient.selectStoreById(businessId);
//            String curierId = tOrder.getCurierId();
//            CourierVo courierVo=new CourierVo();
//            if(curierId!=null){
//                courierVo = courierClient.selectById(curierId);
//            }
//        查询子订单的信息
            QueryWrapper<TSonOrder> wrapper1=new QueryWrapper<>();
            wrapper1.eq("order_id",orderId);
            List<TSonOrder> orderSonlist = sonOrderService.list(wrapper1);
//        整合
            OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();
            orderAndSonOrderVo.setOrder(tOrder);
            orderAndSonOrderVo.setMemberVo(memberVo);
            orderAndSonOrderVo.setStoreVo(storeVo);
            orderAndSonOrderVo.setSonOrderList(orderSonlist);
            orderList.add(orderAndSonOrderVo);
        }

        return orderList;
    }
//无条件查询所有订单
    @Override
    public List<OrderAndSonOrderVo> selectAllOrderByNull() {
        ArrayList<OrderAndSonOrderVo> orderList = new ArrayList<>();
        //        查询订单信息
//        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
//        wrapper.eq("curier_id",cid);
        List<TOrder> orders = baseMapper.selectList(null);
        for (TOrder tOrder : orders) {
//            TOrder tOrder = baseMapper.selectOne(wrapper);
            String orderId = tOrder.getId();
//        得到用户的信息
            String memberId = tOrder.getMemberId();
            MemberVo memberVo = memberClient.selectById(memberId);
//        得到商家的信息
            //        得到商家的信息
            String businessId = tOrder.getBusinessId();
            StoreVo storeVo = storeClient.selectStoreById(businessId);
            String curierId = tOrder.getCurierId();
            CourierVo courierVo=new CourierVo();
            if(curierId!=null){
                courierVo = courierClient.selectById(curierId);
            }
//        查询子订单的信息
            QueryWrapper<TSonOrder> wrapper1=new QueryWrapper<>();
            wrapper1.eq("order_id",orderId);
            List<TSonOrder> orderSonlist = sonOrderService.list(wrapper1);
//        整合
            OrderAndSonOrderVo orderAndSonOrderVo=new OrderAndSonOrderVo();
            orderAndSonOrderVo.setOrder(tOrder);
            orderAndSonOrderVo.setMemberVo(memberVo);
            orderAndSonOrderVo.setStoreVo(storeVo);
            orderAndSonOrderVo.setCourierVo(courierVo);
            orderAndSonOrderVo.setSonOrderList(orderSonlist);
            orderList.add(orderAndSonOrderVo);
        }

        return orderList;
    }
//商家接单，修改时间
    @Override
    public Boolean businessDeliverFood(String id) {
        TOrder tOrder = baseMapper.selectById(id);
        Integer pushTime = tOrder.getPushTime();

        String nowTime = TimeUtils.getNowTime();
        String receiveTime = TimeUtils.getReceiveTime(nowTime, pushTime);

        tOrder.setOrderStatus(2);
        tOrder.setStartTime(nowTime);
        tOrder.setArriveTime(receiveTime);
        int i = baseMapper.updateById(tOrder);
        if(i==1)
            return true;
        else
            return false;
    }
//骑手开始配送，添加骑手id，添加骑手
    @Override
    public Boolean courierDeliverFood(String id, String cid) {
        TOrder tOrder = baseMapper.selectById(id);
        tOrder.setCurierId(cid);
        tOrder.setOrderStatus(3);
        tOrder.setCstartTime(TimeUtils.getNowTime());
        int i = baseMapper.updateById(tOrder);
        if(i==1)
            return true;
        else
            return false;
    }
    //骑手开始配送，到达时间
    @Override
    public Boolean courierArrive(String id, String cid) {
        TOrder tOrder = baseMapper.selectById(id);
        tOrder.setOrderStatus(4);
        tOrder.setCendTime(TimeUtils.getNowTime());
        int i = baseMapper.updateById(tOrder);
        if(i==1)
        {
            Boolean aBoolean = courierClient.addOrderNumber(cid);
            return aBoolean;
        }
        else
            return false;
    }
}
