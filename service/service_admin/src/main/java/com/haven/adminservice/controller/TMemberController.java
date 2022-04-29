package com.haven.adminservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.adminservice.entity.SelectUserVo;
import com.haven.adminservice.entity.TMember;
import com.haven.adminservice.service.TMemberService;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-01
 */
@Api(description = "用户管理模块")
@RestController
@RequestMapping("/adminservice/t-member")
public class TMemberController {
    @Autowired
    TMemberService memberService;
    @ApiOperation("增加用户")
    @PostMapping("/addMember")
    public R addMember(@RequestBody TMember member){
        memberService.save(member);
        return R.ok();
    }
    @ApiOperation("查询用户")
    @GetMapping("selectById/{id}")
    public R selectById(@PathVariable("id") String id){
        TMember member = memberService.getById(id);
        return R.ok().data("member",member);
    }
    @ApiOperation("修改用户")
    @PostMapping("updateById")
    public R updateById(@RequestBody TMember member){
        memberService.updateById(member);
        return R.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable("id") String id){
        memberService.removeById(id);
        return R.ok();
    }

    @ApiOperation("分页查询用户")
    @PostMapping("selectPage/{current}/{limit}")
    public R selectPage(@RequestBody SelectUserVo selectUserVo,
                        @PathVariable("current") int current,
                        @PathVariable("limit") int limit)
                        {
        Page<TMember> page=new Page<>(current,limit);
        QueryWrapper<TMember> queryWrapper=new QueryWrapper<>();
        if(selectUserVo!=null){
            if(selectUserVo.getId()!=null&& StringUtils.isEmpty(selectUserVo.getId())){
                queryWrapper.eq("id",selectUserVo.getId());
            }
            if(selectUserVo.getNickname()!=null&& StringUtils.isEmpty(selectUserVo.getNickname())){
                queryWrapper.like("nickname",selectUserVo.getNickname());
            }
            if(selectUserVo.getMobile()!=null&& StringUtils.isEmpty(selectUserVo.getMobile())){
                queryWrapper.eq("mobile",selectUserVo.getMobile());
            }
        }
          memberService.page(page,queryWrapper);
        List<TMember> memberList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("memberList",memberList).data("total",total);
    }
    @ApiOperation("开启")
    @GetMapping("/openById/{id}")
    public R openById(@PathVariable("id")String id){
        TMember member = memberService.getById(id);
        member.setIsDisabled(false);
        memberService.updateById(member);
        return R.ok();
    }
    @ApiOperation("禁用")
    @GetMapping("/closeById/{id}")
    public R closeById(@PathVariable("id")String id){
        TMember member = memberService.getById(id);
        member.setIsDisabled(true);
        memberService.updateById(member);
        return R.ok();
    }
}

