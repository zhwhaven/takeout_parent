package com.haven.memberservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.memberservice.entity.TAddress;
import com.haven.memberservice.service.TAddressService;
import com.haven.memberservice.vo.RegisterVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/memberservice/t-address")
public class TAddressController {
    @Autowired
    TAddressService addressService;
    @ApiOperation("添加地址")
    @PostMapping("/add")
    public R add(@RequestBody TAddress tAddress){
//        TAddress tAddress = new TAddress();
//        tAddress.setAddress(address);
//        tAddress.setMemberId(usid);
        boolean save = addressService.save(tAddress);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation("删除地址")
    @DeleteMapping("/delete/{adressid}")
    public R delete(@PathVariable("adressid")String adressid){
        boolean b = addressService.removeById(adressid);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation("查询所有地址")
    @GetMapping("/selectByUserid/{usid}")
    public R selectByUserid(@PathVariable("usid")String usid){
        List<TAddress> addressList = addressService.list(new QueryWrapper<TAddress>().eq("member_id", usid));
        return R.ok().data("addressList",addressList);
    }


}

