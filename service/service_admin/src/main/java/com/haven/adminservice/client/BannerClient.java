package com.haven.adminservice.client;

import com.haven.utilscommon.vo.CrmBannerAll;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Component
@FeignClient(value = "cms-service",fallback =BannerClientImpl.class )
public interface BannerClient {
    @GetMapping("/cmsservice/admin-banner/selectAllBanner1")
    @ApiOperation("查询所有背景")
    public List<CrmBannerAll> selectAllBanner1();

    @GetMapping("/cmsservice/admin-banner/selectById/{id}")
    @ApiOperation("根据id查背景")
    public CrmBannerAll selectById(@PathVariable("id")String id);

    @PostMapping("/cmsservice/admin-banner/updataById")
    @ApiOperation("根据id修改背景")
    public Boolean update(@RequestBody CrmBannerAll banner);

    @PostMapping("/cmsservice/admin-banner/add")
    @ApiOperation("添加背景")
    public Boolean add(@RequestBody CrmBannerAll banner);
    @DeleteMapping("/cmsservice/admin-banner/deleteById/{id}")
    @ApiOperation("删除背景")
    public Boolean deleteById(@PathVariable("id")String id);

}
