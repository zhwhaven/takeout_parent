package com.haven.shopcarservice.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "business-service",fallback = FoodClientImpl.class)
public interface FoodClient {
    @ApiOperation("查看食品的库存")
    @GetMapping("/businessservice/api-food/selectFoodInventory/{id}")
    public Integer selectFoodInventory(@PathVariable("id")String id);
}
