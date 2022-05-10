package com.haven.memberservice.controller;


import com.haven.memberservice.entity.TMember;
import com.haven.memberservice.service.TMemberService;
import com.haven.utilscommon.vo.MemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/memberservice/t-member")
public class TMemberController {
    @Autowired
    TMemberService memberService;
      @GetMapping("selectById/{id}")
    public MemberVo selectById(@PathVariable String id){
          TMember tmember = memberService.getById(id);
          MemberVo memberVo=new MemberVo();
          BeanUtils.copyProperties(tmember,memberVo);
          return memberVo;
      }
}

