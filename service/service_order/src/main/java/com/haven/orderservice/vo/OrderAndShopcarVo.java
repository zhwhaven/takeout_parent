package com.haven.orderservice.vo;

import com.haven.orderservice.entity.TOrder;
import com.haven.utilscommon.vo.ShopcarVo;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Data
public class OrderAndShopcarVo {
    TOrder order;
    List<ShopcarVo> shopcarVoList;
}
