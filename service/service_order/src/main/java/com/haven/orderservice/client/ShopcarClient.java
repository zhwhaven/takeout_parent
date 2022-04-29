package com.haven.orderservice.client;

import com.haven.orderservice.client.impl.ShopcarClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "shopcar-service",fallback = ShopcarClientImpl.class)
public interface ShopcarClient {
    @DeleteMapping("/shopcarservice/order-shopcar/deleteShopcar/{id}")
    public Boolean deleteShopcar(@PathVariable("id") String id);
}
