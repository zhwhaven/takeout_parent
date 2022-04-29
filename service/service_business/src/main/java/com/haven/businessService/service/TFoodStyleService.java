package com.haven.businessService.service;

import com.haven.businessService.entity.TFoodStyle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.businessService.vo.StyleVo;

import java.util.ArrayList;

/**
 * <p>
 * 食品种类表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */

public interface TFoodStyleService extends IService<TFoodStyle> {

    ArrayList<StyleVo> getAllStyleByBsid(String id);
}
