package com.haven.adminservice.client;

import com.haven.utilscommon.vo.CrmBannerAll;
import com.haven.utilscommon.vo.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerClientImpl implements BannerClient{
    @Override
    public List<CrmBannerAll> selectAllBanner1() {
        return null;
    }

    @Override
    public CrmBannerAll selectById(String id) {
        return null;

    }

    @Override
    public Boolean update(CrmBannerAll banner) {
        return null;
    }

    @Override
    public Boolean add(CrmBannerAll banner) {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }
}
