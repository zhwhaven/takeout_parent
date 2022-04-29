package com.haven.businessService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.businessService.entity.TFood;
import com.haven.businessService.service.TFoodService;
import com.haven.businessService.vo.SelectFoodVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 食品表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/businessservice/t-food")
@Api(description = "食物管理")
public class TFoodController {

    @Autowired
    TFoodService foodService;
    @ApiOperation("添加食品")
    @PostMapping("/addFood")
    public R addFood(@RequestBody TFood food){
        food.setRealPrice(food.getFoodPrice()*food.getFoodDiscount()/10);
        Integer foodSupply = food.getFoodSupply();
        food.setFoodInventory(foodSupply);
        boolean save = foodService.save(food);
        return R.ok();
    }

    @ApiOperation("删除食品")
    @DeleteMapping("/deleteFood/{id}")
    public R deleteFood(@PathVariable("id")String id){
        foodService.removeById(id);

        return R.ok();
    }
    @ApiOperation("查看食品")
    @GetMapping("/selectFood/{id}")
    public R selectFood(@PathVariable("id")String id){
        TFood food = foodService.getById(id);
        return R.ok().data("food",food);
    }

    @ApiOperation("修改食品")
    @PostMapping("/updateFood")
    public R updateFood(@RequestBody TFood food){
        food.setRealPrice(food.getFoodPrice()*food.getFoodDiscount()/10);
        foodService.updateById(food);
        return R.ok();
    }

    @ApiOperation("商家查看食品")
    @PostMapping("/selectPage/{current}/{limit}/{bsid}")
    public R selectPage(@RequestBody SelectFoodVo selectFoodVo,
                        @PathVariable("current")int current,
                        @PathVariable("limit")int limit,
                        @PathVariable("bsid")String bsid){
        Page<TFood> page=new Page<>(current,limit);
        QueryWrapper<TFood> wrapper=new QueryWrapper<>();
        String id = selectFoodVo.getId();
        String foodName = selectFoodVo.getFoodName();
        String foodDescribe = selectFoodVo.getFoodDescribe();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("id",id);
        }
        if(!StringUtils.isEmpty(foodName)){
            wrapper.like("foodName",foodName);
        }
        if(!StringUtils.isEmpty(foodDescribe)){
            wrapper.like("foodDescribe",foodDescribe);
        }
        wrapper.eq("business_id",bsid);
        IPage<TFood> page1 = foodService.page(page, wrapper);
        List<TFood> foodList = page1.getRecords();
        long total = page1.getTotal();
        return R.ok().data("foodList",foodList).data("total",total);
    }




}

