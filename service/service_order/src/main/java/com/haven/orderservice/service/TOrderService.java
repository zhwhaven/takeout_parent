package com.haven.orderservice.service;

import com.haven.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.orderservice.vo.OrderAndSonOrderVo;
import com.haven.utilscommon.vo.ShopcarVo;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
public interface TOrderService extends IService<TOrder> {

    void makeOrder(TOrder order, List<ShopcarVo> shopcarVoList);
    void pay(String orderid);

    List<OrderAndSonOrderVo> selectAllOrder(String id);

    List<OrderAndSonOrderVo> selectByStatus(String id, Integer status);

    Boolean updateStatus(String orderId, Integer orderId1);

    List<OrderAndSonOrderVo> selectAllOrderByBsid(String bsid);

    List<OrderAndSonOrderVo> selectAllOrderByCid(String cid);

    List<OrderAndSonOrderVo> selectAllOrderByNull();

    Boolean businessDeliverFood(String id);

    Boolean courierDeliverFood(String id, String cid);

    Boolean courierArrive(String id, String cid);
}
