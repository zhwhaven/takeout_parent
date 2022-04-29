package com.haven.orderservice.client;

import com.haven.orderservice.client.impl.FoodClientImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "business-service",fallback = FoodClientImpl.class )
public interface FoodClient {
    @GetMapping("/businessservice/order-service/deleteFoodInventory")
    public Boolean deleteFoodInventory(@RequestParam("foodId")String foodId,
                                       @RequestParam("count")Integer count);
}
