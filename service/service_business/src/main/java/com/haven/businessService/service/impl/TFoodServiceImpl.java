package com.haven.businessService.service.impl;

import com.haven.businessService.entity.TFood;
import com.haven.businessService.mapper.TFoodMapper;
import com.haven.businessService.service.TFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食品表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@Service
public class TFoodServiceImpl extends ServiceImpl<TFoodMapper, TFood> implements TFoodService {

    @Override
    public Boolean deleteFoodInventory(String foodId, Integer count) {
        TFood tFood = baseMapper.selectById(foodId);
        Integer foodInventory = tFood.getFoodInventory();
              foodInventory=foodInventory-count;
              tFood.setFoodInventory(foodInventory);
        int i = baseMapper.updateById(tFood);
        if(i==1)
            return true;
        else
        return null;
    }
}
