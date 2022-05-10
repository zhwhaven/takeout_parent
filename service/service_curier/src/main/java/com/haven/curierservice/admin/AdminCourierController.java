package com.haven.curierservice.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.curierservice.entity.TCourier;
import com.haven.curierservice.service.TCourierService;
import com.haven.curierservice.vo.SelectCourierVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "管理员管理快递员")
@RestController
@RequestMapping("/curierservice/t-admin")
public class AdminCourierController {
    @Autowired
    TCourierService courierService;
      @ApiOperation("分页查询快递员")
    @PostMapping("selectPage/{current}/{limit}")
    public R selectPage(@RequestBody SelectCourierVo selectBusinessVo,
                        @PathVariable("current") int current,
                        @PathVariable("limit") int limit){
        Page<TCourier> page=new Page<>(current,limit);
        QueryWrapper<TCourier> queryWrapper=new QueryWrapper<>();
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
        courierService.page(page,queryWrapper);
        List<TCourier> courierList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("courierList",courierList).data("total",total);
    }
//

    @ApiOperation("分页查询商家申请列表")
    @PostMapping("selectApplyPage/{current}/{limit}")
    public R selectApplyPage(@RequestBody SelectCourierVo selectBusinessVo,
                             @PathVariable("current") int current,
                             @PathVariable("limit") int limit){
        Page<TCourier> page=new Page<>(current,limit);
        QueryWrapper<TCourier> queryWrapper=new QueryWrapper<>();
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
        courierService.page(page,queryWrapper);
        List<TCourier> courierList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("courierList",courierList).data("total",total);
    }

      @ApiOperation("删除快递员")
      @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable("id")String id){
          boolean b = courierService.removeById(id);
          if(b)
              return  R.ok();
          else
              return R.error();
      }
    @ApiOperation("开启快递员")
    @GetMapping("openById/{id}")
    public R openById(@PathVariable("id")String id){
        TCourier business = courierService.getById(id);
        business.setIsDisabled(false);
        courierService.updateById(business);
        return R.ok();
    }
//
    @ApiOperation("关闭快递员")
    @GetMapping("closeById/{id}")
    public R closeById(@PathVariable("id")String id){
        TCourier business = courierService.getById(id);
        business.setIsDisabled(true);
        courierService.updateById(business);
        return R.ok();
    }

    @ApiOperation("同意骑手申请")
    @GetMapping("/agreeApply/{id}")
    public R agreeApply(@PathVariable("id")String id){
        TCourier business = courierService.getById(id);
        business.setIsApply(true);
        courierService.updateById(business);
        return R.ok();
    }


}
