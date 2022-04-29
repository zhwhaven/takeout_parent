package com.haven.businessService.service;

import com.haven.businessService.entity.TFood;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 食品表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
public interface TFoodService extends IService<TFood> {

    Boolean deleteFoodInventory(String foodId, Integer count);
}
