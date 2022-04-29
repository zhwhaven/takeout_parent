package com.haven.adminservice.controller;

import com.haven.adminservice.client.BannerClient;
import com.haven.utilscommon.vo.CrmBannerAll;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminservice/banner")
@Api(description= "管理员管理背景模块")
public class TBannerController {
    @Autowired
    BannerClient bannerClient;
     @GetMapping("selectAllBanner1")
    @ApiOperation("查询所有背景")
    public R selectAllBanner1(){
         List<CrmBannerAll> crmBannerAlls = bannerClient.selectAllBanner1();
         return R.ok().data("bannerList",crmBannerAlls);
     }

     @GetMapping("selectById/{id}")
    @ApiOperation("根据id查背景")
    public R selectById(@PathVariable("id")String id){
         CrmBannerAll banner = bannerClient.selectById(id);
         return R.ok().data("banner",banner);
     }

     @PostMapping("updataById")
    @ApiOperation("根据id修改背景")
    public R update(@RequestBody CrmBannerAll banner){
         Boolean update = bannerClient.update(banner);
         if(update==true){
             return R.ok();
         }else {
             return R.error();

         }     }

    @PostMapping("/add")
    @ApiOperation("添加背景")
    public R add(@RequestBody CrmBannerAll banner) {
        Boolean add = bannerClient.add(banner);
        if(add!=null&&add==true){
            return R.ok();
        }else {
            return R.error();

        }
    }
    @DeleteMapping("deleteById/{id}")
    @ApiOperation("删除背景")
    public R deleteById(@PathVariable("id")String id){
        Boolean aBoolean = bannerClient.deleteById(id);
        if(aBoolean!=null&&aBoolean==true){
            return R.ok();
        }else {
            return R.error();

        }
    }
}
