package com.haven.businessService.order;

import com.haven.businessService.service.TFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(description = "被订单调用的服务-食物")
@RestController
@RequestMapping("/businessservice/order-service")
public class OrderFoodController {
    @Autowired
    TFoodService foodService;
    @ApiOperation("减少库存")
    @GetMapping("deleteFoodInventory")
    public Boolean deleteFoodInventory(@RequestParam("foodId")String foodId,
                                       @RequestParam("count")Integer count){
       return foodService.deleteFoodInventory(foodId,count);
    }
}
