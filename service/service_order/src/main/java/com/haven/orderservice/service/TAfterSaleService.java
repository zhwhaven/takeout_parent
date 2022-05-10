package com.haven.orderservice.service;

import com.haven.orderservice.entity.TAfterSale;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.orderservice.vo.OrderAndSonOrderVo;

/**
 * <p>
 * 售后表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
public interface TAfterSaleService extends IService<TAfterSale> {

    void addAfterSale(TAfterSale afterSale);

    OrderAndSonOrderVo selectOrderMessageById(String id);

    Boolean agreeById(String id);

    Boolean refuseById(String id);
}
