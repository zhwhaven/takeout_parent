package com.haven.orderservice.client.impl;

import com.haven.orderservice.client.StoreClient;
import com.haven.utilscommon.vo.StoreVo;
import org.springframework.stereotype.Component;

@Component
public class StoreClientImpl implements StoreClient {
    @Override
    public StoreVo selectStoreById(String id) {
        return null;
    }
}
