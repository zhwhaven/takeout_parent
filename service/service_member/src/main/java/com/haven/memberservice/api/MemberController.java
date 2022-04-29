package com.haven.memberservice.api;

import com.haven.memberservice.entity.TMember;
import com.haven.memberservice.service.TMemberService;
import com.haven.memberservice.vo.LoginVo;
import com.haven.memberservice.vo.RegisterVo;
import com.haven.utilscommon.util.JwtUtils;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Api(description = "前台用户管理")
@RestController
@RequestMapping("/memberservice/api-member")
public class MemberController {
    @Autowired
    TMemberService memberService;
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }

    @ApiOperation("通过token查询详情")
    @GetMapping("/selectBytoken")
    public R selectBytoken(HttpServletRequest request){

        TMember member=memberService.selectBytoken(request);

        return R.ok().data("member",member);
    }

}
