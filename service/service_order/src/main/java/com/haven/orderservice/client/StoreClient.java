package com.haven.orderservice.client;

import com.haven.orderservice.client.impl.FoodClientImpl;
import com.haven.orderservice.client.impl.StoreClientImpl;
import com.haven.utilscommon.vo.StoreVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "business-service",fallback = StoreClientImpl.class )
public interface StoreClient {
    @GetMapping("/businessservice/order-store/selectStoreById/{id}")
    public StoreVo selectStoreById(@PathVariable String id);

//    @ApiOperation("坏单数量+1")
    @GetMapping("/businessservice/order-store/addBadNumber/{id}")
    public Boolean addBadNumber(@PathVariable String id);
}
