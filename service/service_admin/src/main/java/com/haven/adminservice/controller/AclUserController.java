package com.haven.adminservice.controller;


import com.haven.adminservice.entity.AclUser;
import com.haven.adminservice.service.AclUserService;
import com.haven.adminservice.vo.LoginVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/adminservice/acl-user")
@Api(description= "管理员登陆模块")
//@CrossOrigin
public class AclUserController {

    //    @ApiOperation("登陆")
//    public R login(@RequestBody LoginVo loginVo){
//        String token = service.login(loginVo);
//        return R.ok().data("admin_token",token);
//    }+
    @Autowired
    AclUserService service;
    @PostMapping("/login")
    @ApiOperation("模拟登陆")
    public R login(){
//        String token = service.login(loginVo);
        return R.ok().data("token","admin");
    }
    @GetMapping("/selectByToken")
    @ApiOperation("根据token查询用户信息")
    public R selectByToken(HttpServletRequest request){
        AclUser aclUser = service.selectByToken(request);
        return R.ok().data("aclUser",aclUser);
    }
    @ApiOperation("权限")

    @GetMapping("/info")
    public R getInfo(){
        return R.ok().data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }



}

