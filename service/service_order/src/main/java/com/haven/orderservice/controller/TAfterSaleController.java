package com.haven.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.orderservice.entity.TAfterSale;
import com.haven.orderservice.entity.TComment;
import com.haven.orderservice.service.TAfterSaleService;
import com.haven.orderservice.vo.OrderAndSonOrderVo;
import com.haven.orderservice.vo.SelectAfterSaleVo;
import com.haven.orderservice.vo.SelectCommentVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 售后表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Api(description = "售后模块")
@RestController
@RequestMapping("/orderservice/t-after-sale")
public class TAfterSaleController {

    @Autowired
    TAfterSaleService afterSaleService;

    @ApiOperation("添加售后的申请")
    @PostMapping("/addAfterSale")
    public R addAfterSale(@RequestBody TAfterSale afterSale){
        afterSaleService.addAfterSale(afterSale);
        return R.ok();
    }
//apply_status
    @ApiOperation("分页查询售后")
    @PostMapping("selectAfterSalePage/{current}/{limit}")
    public R selectAfterSalePage(@RequestBody SelectAfterSaleVo selectAfterSaleVo,
                               @PathVariable("current") int current,
                               @PathVariable("limit") int limit){
        Page<TAfterSale> page=new Page<>(current,limit);
        QueryWrapper<TAfterSale> queryWrapper=new QueryWrapper<>();
        if(selectAfterSaleVo!=null){
            if(selectAfterSaleVo.getId()!=null&& StringUtils.isEmpty(selectAfterSaleVo.getId())){
                queryWrapper.eq("id",selectAfterSaleVo.getId());
            }
            if(selectAfterSaleVo.getOrderId()!=null&& StringUtils.isEmpty(selectAfterSaleVo.getOrderId())){
                queryWrapper.eq("order_id",selectAfterSaleVo.getOrderId());
            }
            if(selectAfterSaleVo.getBusinessId()!=null&& StringUtils.isEmpty(selectAfterSaleVo.getBusinessId())){
                queryWrapper.eq("business_id",selectAfterSaleVo.getBusinessId());
            }
            if(selectAfterSaleVo.getMemberId()!=null&& StringUtils.isEmpty(selectAfterSaleVo.getMemberId())){
                queryWrapper.eq("member_id",selectAfterSaleVo.getMemberId());
            }
            if(selectAfterSaleVo.getCurierId()!=null&& StringUtils.isEmpty(selectAfterSaleVo.getCurierId())){
                queryWrapper.eq("curier_id",selectAfterSaleVo.getCurierId());
            }
        }
//        queryWrapper.eq("apply_status",0);
        afterSaleService.page(page,queryWrapper);
        List<TAfterSale> afterSaleList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("afterSaleList",afterSaleList).data("total",total);
    }

    @ApiOperation("根据售后id，得到订单的所有信息")
    @GetMapping("/selectOrderMessageById/{id}")
    public R selectOrderMessageById(@PathVariable String id){
        OrderAndSonOrderVo orderAndSonOrderVo=afterSaleService.selectOrderMessageById(id);
        return R.ok().data("orderAndSonOrderVo",orderAndSonOrderVo);
    }

    @ApiOperation("同意退款")
    @GetMapping("/agreeById/{id}")
    public R agreeById(@PathVariable String id){
        Boolean a=afterSaleService.agreeById(id);
        if(a)
            return R.ok();
        else
            return R.error();
    }
    @ApiOperation("拒绝退款")
    @GetMapping("/refuseById/{id}")
    public R refuseById(@PathVariable String id){
        Boolean a=afterSaleService.refuseById(id);
        if(a)
            return R.ok();
        else
            return R.error();
    }


}

