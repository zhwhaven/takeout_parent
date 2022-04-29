package com.haven.shopcarservice.service;

import com.haven.shopcarservice.entity.TShopcar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.utilscommon.vo.FoodVo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
public interface TShopcarService extends IService<TShopcar> {

    void add(FoodVo food, Integer count, String bsid, String usid);

    void addOne(TShopcar shopcar);

    void cutOne(TShopcar shopcar);

    ArrayList<TShopcar> selectShopcar(String bsid, String usid);

    HashMap<String, Object> selectShopcarCountAndPrice(String bsid, String usid);
}
