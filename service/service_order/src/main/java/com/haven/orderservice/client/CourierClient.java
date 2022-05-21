package com.haven.orderservice.client;

import com.haven.orderservice.client.impl.CourierClientImpl;
import com.haven.orderservice.client.impl.FoodClientImpl;
import com.haven.utilscommon.vo.CourierVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "curier-service",fallback = CourierClientImpl.class )
public interface CourierClient {
//    @ApiOperation("外包团队的注册")
    @GetMapping("/curierservice/t-courier/selectById/{id}")
    public CourierVo selectById(@PathVariable String id);

//    @ApiOperation("订单数量+1")
    @GetMapping("/addOrderNumber/{id}")
    public Boolean addOrderNumber(@PathVariable String id);

//    @ApiOperation("坏的订单数量+1")
    @GetMapping("/addBadNumber/{id}")
    public Boolean addBadNumber(@PathVariable String id);
}
