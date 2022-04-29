package com.haven.businessService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.businessService.entity.TBusiness;
import com.haven.businessService.entity.TBussinessStore;
import com.haven.businessService.mapper.TBusinessMapper;
import com.haven.businessService.mapper.TBussinessStoreMapper;
import com.haven.businessService.service.TBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.businessService.service.TBussinessStoreService;
import com.haven.businessService.vo.LoginVo;
import com.haven.businessService.vo.RegisterVo;
import com.haven.utilscommon.util.JwtUtils;
import com.haven.utilscommon.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家信息表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-10
 */
@Service
public class TBusinessServiceImpl extends ServiceImpl<TBusinessMapper, TBusiness> implements TBusinessService {

    @Autowired
    TBussinessStoreMapper storeMapper;
    @Override
    public void register(RegisterVo registerVo) {
//        查询是否已注册过
        TBusiness business = baseMapper.selectOne(new QueryWrapper<TBusiness>().eq("mobile", registerVo.getMobile()));
        if(business!=null){
            throw new GuliException(20001,"该手机号已注册");
        }else {
            business=new TBusiness();
//            加密处理
            String voPassword = registerVo.getPassword();
            String password = MD5.encrypt(voPassword);
               registerVo.setPassword(password);
            BeanUtils.copyProperties(registerVo,business);
            baseMapper.insert(business);
            String businessId = business.getId();
            storeMapper.insert(new TBussinessStore().setId(businessId));
        }    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String loginVoPassword = loginVo.getPassword();

        System.out.println("mobile"+mobile);
        String password=null;
        if(StringUtils.isEmpty(mobile)){
            new GuliException(20001,"手机号有误");
        }
        if(StringUtils.isEmpty(loginVoPassword)){
            new GuliException(20001,"密码有误");
        }else {
             password = MD5.encrypt(loginVoPassword);
        }
        TBusiness business = baseMapper.selectOne(new QueryWrapper<TBusiness>().eq("mobile", mobile));
        if(business==null){
            new GuliException(20001,"手机号有误");
        }else{
            if(business.getPassword().equals(password)){
                String token = JwtUtils.getJwtToken(business.getId(), business.getRealname());
                return business.getId();
            }else {
                new GuliException(20001,"密码有误");
            }
        }
        return null;
    }
}
