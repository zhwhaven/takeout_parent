package com.haven.shopcarservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.shopcarservice.entity.TShopcar;
import com.haven.shopcarservice.mapper.TShopcarMapper;
import com.haven.shopcarservice.service.TShopcarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.utilscommon.vo.FoodVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@Service
public class TShopcarServiceImpl extends ServiceImpl<TShopcarMapper, TShopcar> implements TShopcarService {

    @Override
    public void add(FoodVo food, Integer count, String bsid, String usid) {
        System.out.println(usid);
        if("null".equals(usid)){
            throw new GuliException(20001,"用户不存在，请登录");
        }


        Double realPrice = food.getRealPrice();
        String foodId = food.getId();
        String foodName = food.getFoodName();
        String foodPicture = food.getFoodPicture();
        Double totalPrice=realPrice*count;
//        判断是否购物车数据是否存在,空则添加，有则修改
        QueryWrapper<TShopcar> wrapper=new QueryWrapper<>();
        wrapper.eq("business_id",bsid);
        wrapper.eq("member_id",usid);
        wrapper.eq("food_id",foodId);
        TShopcar shopcar = baseMapper.selectOne(wrapper);
        if(shopcar==null){
            shopcar=new TShopcar();
           shopcar.setFoodId(foodId);
            shopcar.setFoodName(foodName);
            shopcar.setFoodPicture(foodPicture);
           shopcar.setSinglePrice(realPrice);
           shopcar.setCount(count);
           shopcar.setAllPrice(totalPrice);
           shopcar.setBusinessId(bsid);
           shopcar.setMemberId(usid);
            int insert = baseMapper.insert(shopcar);
            if(insert==0){
                throw new GuliException(20001,"添加购物车失败");
            }
        }else {
              shopcar.setCount(count);
            shopcar.setAllPrice(totalPrice);
            int i = baseMapper.updateById(shopcar);
            if(i==0){
                throw new GuliException(20001,"添加购物车失败");
            }
        }
        }



    @Override
    public void addOne(TShopcar shopcar) {


        Integer count = shopcar.getCount()+1;
        shopcar.setCount(count);
        Double singlePrice = shopcar.getSinglePrice();
        Double allPrice = shopcar.getAllPrice();
        shopcar.setAllPrice(allPrice+singlePrice);
        int i = baseMapper.updateById(shopcar);
        if(i==0){
            throw new GuliException(20001,"购物车+1失败");
        }

    }

    @Override
    public void cutOne(TShopcar shopcar) {

//        购物车数量为1时直接删除
        Integer count = shopcar.getCount();
        Double singlePrice = shopcar.getSinglePrice();
        Double allPrice = shopcar.getAllPrice();
        shopcar.setAllPrice(allPrice-singlePrice);
        if(count==1){
            baseMapper.deleteById(shopcar.getId());
        }else{
            shopcar.setCount(count-1);
            baseMapper.updateById(shopcar);
        }
    }
//
    @Override
    public ArrayList<TShopcar> selectShopcar(String bsid, String usid) {
        if("null".equals(usid)){
            throw new GuliException(20001,"用户不存在，请登录");
        }
        QueryWrapper<TShopcar> wrapper=new QueryWrapper<>();
         wrapper.eq("business_id",bsid);
        wrapper.eq("member_id",usid);
        ArrayList<TShopcar> list = (ArrayList<TShopcar>) baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public HashMap<String, Object> selectShopcarCountAndPrice(String bsid, String usid) {
        if("null".equals(usid)){
            throw new GuliException(20001,"用户不存在，请登录");
        }
        HashMap<String,Object> map=baseMapper.selectShopcarCountAndPrice(bsid,usid);
        return map;
    }
}
