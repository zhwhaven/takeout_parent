package com.haven.memberservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.memberservice.entity.TMember;
import com.haven.memberservice.mapper.TMemberMapper;
import com.haven.memberservice.service.TMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.memberservice.vo.LoginVo;
import com.haven.memberservice.vo.RegisterVo;
import com.haven.utilscommon.util.JwtUtils;
import com.haven.utilscommon.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@Service
public class TMemberServiceImpl extends ServiceImpl<TMemberMapper, TMember> implements TMemberService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String code = registerVo.getCode();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        TMember member = baseMapper.selectOne(new QueryWrapper<TMember>().eq("mobile", mobile));
        if(member!=null){
            throw new GuliException(20001,"该用户已存在");
        }else {
            String recode = redisTemplate.opsForValue().get(mobile);
            if(recode!=null&&recode.equals(code)){
                 member=new TMember();
                String encrypt = MD5.encrypt(password);
                member.setAvatar("https://havenguli.oss-cn-guangzhou.aliyuncs.com/2022/04/03/64997d40-1ded-4a03-a12f-0152a6d12e42/file.png");
                member.setNickname(nickname);
                member.setPassword(encrypt);
                member.setMobile(mobile);
                member.setSign("新人一个，说些什么吧");
                baseMapper.insert(member);

            }else {
                throw new GuliException(20001,"验证码错误");

            }
        }

    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        TMember member = baseMapper.selectOne(new QueryWrapper<TMember>().eq("mobile", mobile));
        if(member==null){
            throw new GuliException(20001,"该账号不存在，请注册后登录");
        }else {
            String encrypt = MD5.encrypt(password);
            TMember tMember = baseMapper.selectOne
                    (new QueryWrapper<TMember>().eq("mobile", mobile).eq("password", encrypt));
            if(tMember==null){
                throw new GuliException(20001,"账号或密码有误");
            }else {
                String token = JwtUtils.getJwtToken(tMember.getId(), tMember.getNickname());
                return token;
            }
        }
    }

    @Override
    public TMember selectBytoken(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if(!StringUtils.isEmpty(id)){
            TMember member = baseMapper.selectById(id);
            return member;
        }
        return null;
    }
}
