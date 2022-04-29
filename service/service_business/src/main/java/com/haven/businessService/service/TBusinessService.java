package com.haven.businessService.service;

import com.haven.businessService.entity.TBusiness;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.businessService.vo.LoginVo;
import com.haven.businessService.vo.RegisterVo;

/**
 * <p>
 * 商家信息表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-10
 */
public interface TBusinessService extends IService<TBusiness> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);
}
