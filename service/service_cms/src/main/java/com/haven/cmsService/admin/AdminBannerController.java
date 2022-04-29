package com.haven.cmsService.admin;

import com.haven.cmsService.entity.CrmBanner;
import com.haven.cmsService.service.CrmBannerService;
import com.haven.utilscommon.vo.CrmBannerAll;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cmsservice/admin-banner")
@Api(description = "管理员端的首页背景管理")
public class AdminBannerController {

    @Autowired
    CrmBannerService bannerService;
    @GetMapping("/selectAllBanner1")
    @ApiOperation("查询所有背景")
    public List<CrmBannerAll> selectAllBanner1(){
        List<CrmBanner> banners = bannerService.list(null);
        ArrayList<CrmBannerAll> bannerList=new ArrayList<>();
        for (CrmBanner banner : banners) {
            CrmBannerAll a=new CrmBannerAll();
            BeanUtils.copyProperties(banner,a);
            bannerList.add(a);
        }
        return bannerList;
    }

    @GetMapping("/selectById/{id}")
    @ApiOperation("根据id查背景")
    public CrmBannerAll selectById(@PathVariable("id")String id){
        CrmBanner crmBanner = bannerService.getById(id);
        CrmBannerAll banner=new CrmBannerAll();
        BeanUtils.copyProperties(crmBanner,banner);
        return banner;
    }

    @PostMapping("/updataById")
    @ApiOperation("根据id修改背景")
    public Boolean update(@RequestBody CrmBannerAll banner){
        CrmBanner crmBanner=new CrmBanner();
        BeanUtils.copyProperties(banner,crmBanner);
        boolean b = bannerService.updateById(crmBanner);
        return b;
    }
    @PostMapping("/add")
    @ApiOperation("添加背景")
    public Boolean add(@RequestBody CrmBannerAll banner){
        CrmBanner crmBanner=new CrmBanner();
        BeanUtils.copyProperties(banner,crmBanner);
        boolean save = bannerService.save(crmBanner);
        return save;
    }
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation("删除背景")
    public Boolean deleteById(@PathVariable("id")String id){
        boolean b = bannerService.removeById(id);
        return b;
    }

}
