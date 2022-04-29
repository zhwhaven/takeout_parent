package com.haven.adminservice.controller;


import com.haven.adminservice.service.TFoodStyleService;
import com.haven.adminservice.vo.FirstStyleVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 食品种类表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/adminservice/t-food-style")
@Api(description = "食物分类管理")
public class TFoodStyleController {

    @Autowired
    TFoodStyleService styleService;
    @ApiOperation("上传文件，得到食物分类")
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        styleService.upload(file,styleService);
        return R.ok();
    }

    @ApiOperation("查询所有分类")
    @GetMapping("/selectAllStyle")
    public R selectAllStyle(){
        List<FirstStyleVo> styleList=styleService.selectAllStyle();
        return R.ok().data("styleList",styleList);
    }

}

