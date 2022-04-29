package com.haven.shopcarservice.mapper;

import com.haven.shopcarservice.entity.TShopcar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
public interface TShopcarMapper extends BaseMapper<TShopcar> {
    HashMap<String, Object> selectShopcarCountAndPrice(@Param("bsid") String bsid, @Param("usid") String usid);

//    ArrayList<TShopcar> selectShopcar(@Param("bsid") String bsid,@Param("usid") String usid);
}
