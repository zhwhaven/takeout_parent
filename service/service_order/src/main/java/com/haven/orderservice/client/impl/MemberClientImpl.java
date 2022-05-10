package com.haven.orderservice.client.impl;

import com.haven.orderservice.client.MemberClient;
import com.haven.utilscommon.vo.MemberVo;
import org.springframework.stereotype.Component;

@Component
public class MemberClientImpl implements MemberClient {
    @Override
    public MemberVo selectById(String id) {
        return null;
    }
}
