package com.haven.orderservice.vo;

import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.entity.TSonOrder;
import com.haven.utilscommon.vo.CourierVo;
import com.haven.utilscommon.vo.MemberVo;
import com.haven.utilscommon.vo.ShopcarVo;
import com.haven.utilscommon.vo.StoreVo;
import lombok.Data;

import java.util.List;
@Data
public class OrderAndSonOrderVo {
    TOrder order;
    StoreVo storeVo;
    CourierVo courierVo;
    MemberVo memberVo;
    List<TSonOrder> sonOrderList ;
}
