package com.haven.businessService.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.businessService.entity.TFood;
import com.haven.businessService.service.TFoodService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/businessservice/api-food")
@Api(description = "用户查看有关商家的食物")
public class ApiFoodController {
    @Autowired
    TFoodService foodService;

    @ApiOperation("根据商家id查找所有的食物")
    @GetMapping("selectFoodListByBsid/{bsid}")
    public R selectFoodListByBsid(@PathVariable("bsid") String bsid){
        QueryWrapper<TFood> wrapper=new QueryWrapper<>();
        wrapper.eq("business_id",bsid);
        List<TFood> foodList = foodService.list(wrapper);
        return R.ok().data("foodList",foodList);
    }
    @ApiOperation("根据商家和类型id查找所有的食物")
    @GetMapping("selectFoodListByBsidAndStyleId/{bsid}/{styleid}")
    public R selectFoodListByBsidAndStyleId(@PathVariable("bsid") String bsid,
                                            @PathVariable("styleid") String styleid){
        QueryWrapper<TFood> wrapper=new QueryWrapper<>();
        wrapper.eq("business_id",bsid);
        wrapper.eq("style_id",styleid);
        List<TFood> foodList = foodService.list(wrapper);
        return R.ok().data("foodList",foodList);
    }
    @ApiOperation("查看食品")
    @GetMapping("/selectFood/{id}")
    public R selectFood(@PathVariable("id")String id){
        TFood food = foodService.getById(id);
        return R.ok().data("food",food);
    }

    @ApiOperation("查看食品的库存")
    @GetMapping("/selectFoodInventory/{id}")
    public Integer selectFoodInventory(@PathVariable("id")String id){
        TFood food = foodService.getById(id);
        return food.getFoodInventory();
    }

}
