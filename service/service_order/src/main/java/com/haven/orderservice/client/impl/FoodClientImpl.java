package com.haven.orderservice.client.impl;

import com.haven.orderservice.client.FoodClient;
import org.springframework.stereotype.Component;

@Component
public class FoodClientImpl implements FoodClient {
    @Override
    public Boolean deleteFoodInventory(String foodId, Integer count) {
        return null;
    }
}
