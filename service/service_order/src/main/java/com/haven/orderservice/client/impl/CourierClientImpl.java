package com.haven.orderservice.client.impl;

import com.haven.orderservice.client.CourierClient;
import com.haven.utilscommon.vo.CourierVo;
import org.springframework.stereotype.Component;

@Component
public class CourierClientImpl implements CourierClient {
    @Override
    public CourierVo selectById(String id) {
        return null;
    }

    @Override
    public Boolean addOrderNumber(String id) {
        return null;
    }

    @Override
    public Boolean addBadNumber(String id) {
        return null;
    }
}
