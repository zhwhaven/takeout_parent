package com.haven.adminservice.service;

import com.haven.adminservice.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.adminservice.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-28
 */
public interface AclUserService extends IService<AclUser> {

    String login(LoginVo loginVo);

    AclUser selectByToken(HttpServletRequest request);
}
