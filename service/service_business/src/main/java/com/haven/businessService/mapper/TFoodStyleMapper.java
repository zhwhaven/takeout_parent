package com.haven.businessService.mapper;

import com.haven.businessService.entity.TFoodStyle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haven.businessService.vo.StyleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 食品种类表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */
@Mapper
public interface TFoodStyleMapper extends BaseMapper<TFoodStyle> {

    ArrayList<StyleVo> selectStyleListByIdList(@Param("idList") List<String> idList);

}
