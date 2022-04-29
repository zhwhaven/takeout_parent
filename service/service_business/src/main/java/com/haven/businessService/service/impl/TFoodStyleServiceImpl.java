package com.haven.businessService.service.impl;

import com.haven.businessService.entity.TFoodStyle;
import com.haven.businessService.mapper.TFoodMapper;
import com.haven.businessService.mapper.TFoodStyleMapper;
import com.haven.businessService.service.TFoodStyleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.businessService.vo.StyleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 食品种类表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */
@Service
public class
TFoodStyleServiceImpl extends ServiceImpl<TFoodStyleMapper, TFoodStyle> implements TFoodStyleService {

    @Autowired
    TFoodMapper foodMapper;
    @Autowired
    TFoodStyleMapper styleMapper;
    @Override
    public ArrayList<StyleVo> getAllStyleByBsid(String id) {
//        根据商家id查出所有的食物种类
        List<String> idList= foodMapper.selectStyleIdList(id);

//        根据食物种类的id集合得到StyleVo的集合信息
        ArrayList<StyleVo> styleVos=styleMapper.selectStyleListByIdList(idList);
        return styleVos;
    }
}
