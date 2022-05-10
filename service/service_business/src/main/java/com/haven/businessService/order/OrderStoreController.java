package com.haven.businessService.order;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.haven.businessService.entity.TBussinessStore;
import com.haven.businessService.service.TBussinessStoreService;
import com.haven.businessService.service.TFoodService;
import com.haven.utilscommon.vo.StoreVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "被订单调用的服务-店铺")
@RestController
@RequestMapping("/businessservice/order-store")
public class OrderStoreController {
    @Autowired
    TBussinessStoreService storeService;
    @ApiOperation("通过商家id得到店铺信息")
    @GetMapping("selectStoreById/{id}")
    public StoreVo selectStoreById(@PathVariable String id){
        TBussinessStore bussinessStore = storeService.getById(id);
        StoreVo storeVo=new StoreVo();
        BeanUtils.copyProperties(bussinessStore,storeVo);
        return storeVo;
    }
}
