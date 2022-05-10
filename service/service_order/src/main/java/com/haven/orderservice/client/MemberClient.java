package com.haven.orderservice.client;

import com.haven.orderservice.client.impl.MemberClientImpl;
import com.haven.orderservice.client.impl.ShopcarClientImpl;
import com.haven.utilscommon.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "member-service",fallback = MemberClientImpl.class)
public interface MemberClient {
    @GetMapping("selectById/{id}")
    public MemberVo selectById(@PathVariable String id);
}
