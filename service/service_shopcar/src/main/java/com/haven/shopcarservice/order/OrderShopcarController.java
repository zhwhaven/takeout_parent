package com.haven.shopcarservice.order;

import com.haven.shopcarservice.entity.TShopcar;
import com.haven.shopcarservice.service.TShopcarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "被订单调用的购物车模块")
@RestController
@RequestMapping("/shopcarservice/order-shopcar")
public class OrderShopcarController {
    @Autowired
    TShopcarService shopcarService;
    @ApiOperation("删除购物车")
    @DeleteMapping("/deleteShopcar/{id}")
    public Boolean deleteShopcar(@PathVariable("id") String id){
        boolean b = shopcarService.removeById(id);
        return b;
    }
}
