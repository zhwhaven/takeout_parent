package com.haven.businessService.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.businessService.entity.TBusiness;
import com.haven.businessService.entity.TBussinessStore;
import com.haven.businessService.service.TBusinessService;
import com.haven.businessService.service.TBussinessStoreService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/businessservice/api-business")
@Api(description = "显示商家的信息")
public class ApiBusinessController {
    @Autowired
    TBusinessService businessService;
    @Autowired
    TBussinessStoreService storeService;

    @ApiOperation("查询商家个人信息")
    @GetMapping("selectById/{id}")
    public R selectById(@PathVariable("id") String id){
        TBusiness business = businessService.getById(id);
        return R.ok().data("business",business);
    }
    @ApiOperation("查询商家店铺信息")
    @GetMapping("selectStoreById/{id}")
    public R selectStoreById(@PathVariable("id") String id){
        TBussinessStore store = storeService.getById(id);
        return R.ok().data("store",store);
    }

    @ApiOperation("根据地址推荐所有的店铺信息")
    @GetMapping("selectStoreByAddress/{address}")
    public R selectStoreByAddress(@PathVariable("address") String address){
        QueryWrapper<TBussinessStore> wrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(address)){
            wrapper.like("store_address",address);
        }
        List<TBussinessStore> businessList = storeService.list(wrapper);
        return R.ok().data("businessList",businessList);
    }

    @ApiOperation("根据地址查询销售前八的店铺")
    @GetMapping("selectStoreRankByAddress/{address}")
    public R selectStoreRankByAddress(@PathVariable("address") String address){
        QueryWrapper<TBussinessStore> wrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(address)){
            wrapper.like("store_address",address);
        }
        wrapper.last("limit 8");
        List<TBussinessStore> storeRankList = storeService.list(wrapper);
        return R.ok().data("storeRankList",storeRankList);
    }

}
