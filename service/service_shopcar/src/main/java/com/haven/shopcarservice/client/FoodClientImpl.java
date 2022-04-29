package com.haven.shopcarservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class FoodClientImpl implements FoodClient{
    public Integer selectFoodInventory(@PathVariable("id")String id){
        return null;
    }
}
