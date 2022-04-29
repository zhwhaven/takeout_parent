package com.haven.memberservice.service;

import com.haven.memberservice.entity.TMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.memberservice.vo.LoginVo;
import com.haven.memberservice.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
public interface TMemberService extends IService<TMember> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    TMember selectBytoken(HttpServletRequest request);
}
