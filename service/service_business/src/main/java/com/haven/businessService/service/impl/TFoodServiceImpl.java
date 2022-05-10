package com.haven.businessService.service.impl;

import com.haven.businessService.entity.TBussinessStore;
import com.haven.businessService.entity.TFood;
import com.haven.businessService.mapper.TBussinessStoreMapper;
import com.haven.businessService.mapper.TFoodMapper;
import com.haven.businessService.service.TFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TBussinessStoreMapper storeMapper;
    @Override
    public Boolean deleteFoodInventory(String foodId, Integer count) {
        TFood tFood = baseMapper.selectById(foodId);
        Integer foodInventory = tFood.getFoodInventory();
        Integer foodCount = tFood.getFoodCount();
        String businessId = tFood.getBusinessId();
        foodInventory=foodInventory-count;
        foodCount+=count;
              tFood.setFoodInventory(foodInventory);
              tFood.setFoodCount(foodCount);
        int i = baseMapper.updateById(tFood);
        if(i==1){
            TBussinessStore store = storeMapper.selectById(businessId);
            int storeFoodCount = store.getFoodCount();
            storeFoodCount+=count;
            store.setFoodCount(storeFoodCount);
            int i1 = storeMapper.updateById(store);
            if(i1==1){
                return true;
            }else {
                return false;
            }
        }
       return false;
    }
}
