package com.haven.businessService.controller;


import com.haven.businessService.entity.TBusiness;
import com.haven.businessService.entity.TBussinessStore;
import com.haven.businessService.service.TBusinessService;
import com.haven.businessService.service.TBussinessStoreService;
import com.haven.businessService.vo.LoginVo;
import com.haven.businessService.vo.RegisterVo;
import com.haven.utilscommon.util.JwtUtils;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 商家信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/businessservice/t-business")
@Api(description = "商家注册登陆")
public class TBusinessController {

    @Autowired
    TBusinessService businessService;
    @Autowired
    TBussinessStoreService storeService;

    @ApiOperation("商家注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        businessService.register(registerVo);
        return R.ok();
    }
    @ApiOperation("登陆")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        String mobile = loginVo.getUsername();
        loginVo.setMobile(mobile);
        String bsid=businessService.login(loginVo);
        TBusiness business;
        if(bsid==null){
            return R.error();
        }else {
            business = businessService.getById(bsid);
        }
        return R.ok().data("token","admin").data("bsid",bsid).data("realname",business.getRealname());
    }
    @ApiOperation("注销")
    @GetMapping("/logout")
    public R logout(){
        return R.ok();
    }
    @ApiOperation("权限")
    @GetMapping("/info")
    public R getInfo(@RequestParam("token")String token){
        return R.ok().data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
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
    @ApiOperation("修改商家个人信息")
    @PostMapping("updateById")
    public R updateById(@RequestBody TBusiness business){
        boolean b = businessService.updateById(business);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("修改商家店铺信息")
    @PostMapping("/updateStroreById")
    public R updateStroreById(@RequestBody TBussinessStore store){

        String id = store.getId();
        TBussinessStore isStore = storeService.getById(id);
        if(isStore!=null){
            boolean b = storeService.updateById(store);
            if(b){
                return R.ok();
            }else{
                return R.error();
            }
        }else {
            boolean save = storeService.save(store);
            if(save){
                return R.ok();
            }else{
                return R.error();
            }

        }

    }
}

