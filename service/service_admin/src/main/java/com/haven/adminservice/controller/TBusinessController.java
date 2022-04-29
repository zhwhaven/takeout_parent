package com.haven.adminservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.adminservice.entity.SelectUserVo;
import com.haven.adminservice.entity.TBusiness;
import com.haven.adminservice.entity.TBussinessStore;
import com.haven.adminservice.entity.TMember;
import com.haven.adminservice.service.TBusinessService;
import com.haven.adminservice.service.TBussinessStoreService;
import com.haven.adminservice.service.TMemberService;
import com.haven.adminservice.vo.BusinessAndStoreVo;
import com.haven.adminservice.vo.SelectBusinessVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商家信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-01
 */
@RestController
@RequestMapping("/adminservice/t-business")
@Api(description = "商家管理模块")
public class TBusinessController {
    @Autowired
    TBusinessService businessService;
    @Autowired
    TBussinessStoreService storeService;
    @ApiOperation("增加商家")
    @PostMapping("/add")
    public R add(@RequestBody TBusiness business){
        businessService.save(business);
        return R.ok();
    }

    @ApiOperation("同意商家申请")
    @GetMapping("/agreeApply/{id}")
    public R agreeApply(@PathVariable("id")String id){
        TBusiness business = businessService.getById(id);
        business.setIsApply(true);
        businessService.updateById(business);
        return R.ok();
    }

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

    @ApiOperation("查询商家和店铺信息")
    @GetMapping("selectBusinessAndStoreById/{id}")
    public R selectBusinessAndStoreById(@PathVariable("id") String id){
        BusinessAndStoreVo businessForm=businessService.selectBusinessAndStoreById(id);
        return R.ok().data("businessForm",businessForm);
    }

    @ApiOperation("修改商家和店铺信息")
    @PostMapping("updateBusinessAndStoreById")
    public R updateBusinessAndStoreById(@RequestBody BusinessAndStoreVo businessAndStoreVo){
        businessService.updateBusinessAndStoreById(businessAndStoreVo);
        return R.ok();
    }


    @ApiOperation("分页查询商家")
    @PostMapping("selectPage/{current}/{limit}")
    public R selectPage(@RequestBody SelectBusinessVo selectBusinessVo,
                        @PathVariable("current") int current,
                        @PathVariable("limit") int limit){
        Page<TBusiness> page=new Page<>(current,limit);
        QueryWrapper<TBusiness> queryWrapper=new QueryWrapper<>();
        if(selectBusinessVo!=null){
            if(selectBusinessVo.getId()!=null&& StringUtils.isEmpty(selectBusinessVo.getId())){
                queryWrapper.eq("id",selectBusinessVo.getId());
            }
            if(selectBusinessVo.getRealname()!=null&& StringUtils.isEmpty(selectBusinessVo.getRealname())){
                queryWrapper.like("realname",selectBusinessVo.getRealname());
            }
            if(selectBusinessVo.getMobile()!=null&& StringUtils.isEmpty(selectBusinessVo.getMobile())){
                queryWrapper.eq("mobile",selectBusinessVo.getMobile());
            }
        }
        queryWrapper.eq("is_apply",1);
        businessService.page(page,queryWrapper);
        List<TBusiness> businessList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("businessList",businessList).data("total",total);
    }

//后面需要改进，添加删除关联的食物
      @ApiOperation("删除商家")
      @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable("id")String id){
        businessService.removeById(id);
        storeService.removeById(id);
        return  R.ok();
      }
    @ApiOperation("开启商家")
    @GetMapping("openById/{id}")
    public R openById(@PathVariable("id")String id){
        TBusiness business = businessService.getById(id);
        business.setIsDisabled(false);
        businessService.updateById(business);
        TBussinessStore store = storeService.getById(id);
        store.setIsDisabled(false);
        storeService.updateById(store);
        return R.ok();
    }

    @ApiOperation("关闭商家")
    @GetMapping("closeById/{id}")
    public R closeById(@PathVariable("id")String id){
        TBusiness business = businessService.getById(id);
        business.setIsDisabled(true);
        businessService.updateById(business);
        TBussinessStore store = storeService.getById(id);
        store.setIsDisabled(true);
        storeService.updateById(store);
        return R.ok();
    }



    @ApiOperation("分页查询商家申请列表")
    @PostMapping("selectApplyPage/{current}/{limit}")
    public R selectApplyPage(@RequestBody SelectBusinessVo selectBusinessVo,
                        @PathVariable("current") int current,
                        @PathVariable("limit") int limit){
        Page<TBusiness> page=new Page<>(current,limit);
        QueryWrapper<TBusiness> queryWrapper=new QueryWrapper<>();
        if(selectBusinessVo!=null){
            if(selectBusinessVo.getId()!=null&& StringUtils.isEmpty(selectBusinessVo.getId())){
                queryWrapper.eq("id",selectBusinessVo.getId());
            }
            if(selectBusinessVo.getRealname()!=null&& StringUtils.isEmpty(selectBusinessVo.getRealname())){
                queryWrapper.like("realname",selectBusinessVo.getRealname());
            }
            if(selectBusinessVo.getMobile()!=null&& StringUtils.isEmpty(selectBusinessVo.getMobile())){
                queryWrapper.eq("mobile",selectBusinessVo.getMobile());
            }
        }
        queryWrapper.eq("is_apply",0);
        businessService.page(page,queryWrapper);
        List<TBusiness> businessList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("businessList",businessList).data("total",total);
    }

}

