package com.haven.businessService.api;

import com.haven.businessService.service.TFoodStyleService;
import com.haven.businessService.vo.StyleVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Api(description = "查询商家所有的食物种类")
@RestController
@RequestMapping("/businessservice/api-style")
public class ApiStyleController {
    @Autowired
    TFoodStyleService styleService;
    @ApiOperation("通过商家id查所有种类")
    @GetMapping("/getAllStyleByBsid/{id}")
    public R getAllStyleByBsid(@PathVariable String id){
        ArrayList<StyleVo> styleList=styleService.getAllStyleByBsid(id);
        return R.ok().data("styleList",styleList);
    }

}
