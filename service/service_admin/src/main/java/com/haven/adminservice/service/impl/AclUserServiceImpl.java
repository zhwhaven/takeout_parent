package com.haven.adminservice.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.adminservice.entity.AclUser;
import com.haven.adminservice.mapper.AclUserMapper;
import com.haven.adminservice.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.adminservice.vo.LoginVo;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.utilscommon.util.JwtUtils;
import com.haven.utilscommon.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-28
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

    @Override
    public String login(LoginVo loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"账号或密码有误");
        }
        String encrypt = MD5.encrypt(password);
        QueryWrapper<AclUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",encrypt);
        AclUser aclUser = baseMapper.selectOne(wrapper);
        if(aclUser!=null){
            String id = aclUser.getId();
            String nickName = aclUser.getNickName();
            String jwtToken = JwtUtils.getJwtToken(id, nickName);
            return jwtToken;
        }else {
            throw new GuliException(20001,"账号或密码有误");

        }



    }

    @Override
    public AclUser selectByToken(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        AclUser aclUser = baseMapper.selectOne(new QueryWrapper<AclUser>().eq("id", id));
        return aclUser;
    }
}
