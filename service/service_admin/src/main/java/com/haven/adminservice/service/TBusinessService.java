package com.haven.adminservice.service;

import com.haven.adminservice.entity.TBusiness;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.adminservice.vo.BusinessAndStoreVo;

/**
 * <p>
 * 商家信息表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-01
 */
public interface TBusinessService extends IService<TBusiness> {

    BusinessAndStoreVo selectBusinessAndStoreById(String id);

    void updateBusinessAndStoreById(BusinessAndStoreVo businessAndStoreVo);
}
