package com.haven.shopcarservice.controller;


import com.haven.shopcarservice.client.FoodClient;
import com.haven.shopcarservice.entity.TShopcar;
import com.haven.shopcarservice.service.TShopcarService;
import com.haven.utilscommon.vo.FoodVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@Api(description = "购物车管理")
@RestController
@RequestMapping("/shopcarservice/t-shopcar")
public class TShopcarController {

    @Autowired
    TShopcarService shopcarService;

    @Autowired
    FoodClient foodClient;
    @ApiOperation("添加到购物车")
    @PostMapping("/add/{count}/{bsid}/{usid}")
    public R add(@RequestBody FoodVo food,
                 @PathVariable Integer count,
                 @PathVariable String bsid,
                 @PathVariable String usid
                 ){
        shopcarService.add(food,count,bsid,usid);
        return R.ok();
    }
    @ApiOperation("删除购物车")
    @DeleteMapping("deleteShopcarById/{id}")
    public R deleteShopcarById(@PathVariable String id){
        boolean b = shopcarService.removeById(id);
        if(b==true){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation("购物车的加一操作")
    @PostMapping("/addOne")
    public R addOne(@RequestBody TShopcar shopcar){
        System.out.println(shopcar);
        Integer inventory = foodClient.selectFoodInventory(shopcar.getFoodId());
        if(inventory==0||inventory==null){
        }else {
            shopcarService.addOne(shopcar);
        }
        return R.ok();
    }
    @ApiOperation("购物车的减一操作")
    @PostMapping("/cutOne")
    public R cutOne(@RequestBody TShopcar shopcar){
        System.out.println(shopcar);
         shopcarService.cutOne(shopcar);
        return R.ok();
    }

    @ApiOperation("查询购物车")
    @GetMapping("/selectShopcar/{bsid}/{usid}")
    public R selectShopcar(@PathVariable("bsid") String bsid,
                           @PathVariable("usid") String usid){
        ArrayList<TShopcar> shopcarList=shopcarService.selectShopcar(bsid,usid);
        return R.ok().data("shopcarList",shopcarList);
    }

    @ApiOperation("查询购物车的总数和总价")
    @GetMapping("/selectShopcarCountAndPrice/{bsid}/{usid}")
    public R selectShopcarCountAndPrice(@PathVariable("bsid") String bsid,
                           @PathVariable("usid") String usid){
        HashMap<String,Object> map=new HashMap<>();
       map=shopcarService.selectShopcarCountAndPrice(bsid,usid);
        Integer totalCount=0;
        Double totalPrice=0.0;
       if(map!=null){
        Object totalCount1 = map.get("TotalCount");
        if(totalCount1!=null){
            String s = String.valueOf(totalCount1);
        totalCount = Integer.valueOf(s);
        }

        Object totalPrice1 = map.get("TotalPrice");
        if(totalPrice1!=null){
            String x = String.valueOf(totalPrice1);
           totalPrice = Double.valueOf(x);
        }}

        return R.ok().data("totalCount",totalCount).data("totalPrice",totalPrice);
    }

}

