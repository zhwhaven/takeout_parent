package com.haven.orderservice.service;

import com.haven.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
