package com.haven.adminservice.service.impl;

import com.haven.adminservice.entity.TMember;
import com.haven.adminservice.mapper.TMemberMapper;
import com.haven.adminservice.service.TMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-01
 */
@Service
public class TMemberServiceImpl extends ServiceImpl<TMemberMapper, TMember> implements TMemberService {

}
