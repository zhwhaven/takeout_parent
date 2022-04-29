package com.haven.orderservice.service.impl;

import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.orderservice.client.FoodClient;
import com.haven.orderservice.client.ShopcarClient;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.entity.TSonOrder;
import com.haven.orderservice.mapper.TOrderMapper;
import com.haven.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.orderservice.service.TSonOrderService;
import com.haven.orderservice.util.TimeUtils;
import com.haven.utilscommon.vo.ShopcarVo;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
                    Boolean aBoolean = foodClient.deleteFoodInventory(foodId, count);
                    if(aBoolean==false){
                        throw new GuliException(20001,"减去库存失败");

                    }
//            删除购物车
                    Boolean aBoolean1 = shopcarClient.deleteShopcar(shopcarId);
                    if(aBoolean1==false){
                        throw new GuliException(20001,"删除购物车失败");
                    }
            }

        }

        }
    }
}
