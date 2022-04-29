package com.haven.adminservice.service.impl;

import com.haven.adminservice.entity.TBusiness;
import com.haven.adminservice.entity.TBussinessStore;
import com.haven.adminservice.mapper.TBusinessMapper;
import com.haven.adminservice.mapper.TBussinessStoreMapper;
import com.haven.adminservice.service.TBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.adminservice.vo.BusinessAndStoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家信息表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-01
 */
@Service
public class TBusinessServiceImpl extends ServiceImpl<TBusinessMapper, TBusiness> implements TBusinessService {

    @Autowired
    TBussinessStoreMapper storeMapper;
    @Override
    public BusinessAndStoreVo selectBusinessAndStoreById(String id) {
        TBusiness tBusiness = baseMapper.selectById(id);
        TBussinessStore tBussinessStore = storeMapper.selectById(id);
        BusinessAndStoreVo businessAndStoreVo=new BusinessAndStoreVo();
        BeanUtils.copyProperties(tBusiness,businessAndStoreVo);
        BeanUtils.copyProperties(tBussinessStore,businessAndStoreVo);
        return businessAndStoreVo;
    }

    @Override
    public void updateBusinessAndStoreById(BusinessAndStoreVo businessAndStoreVo) {
        TBusiness business=new TBusiness();
        TBussinessStore store=new TBussinessStore();
        BeanUtils.copyProperties(businessAndStoreVo,business);
        BeanUtils.copyProperties(businessAndStoreVo,store);
        baseMapper.updateById(business);
        storeMapper.updateById(store);
    }
}
