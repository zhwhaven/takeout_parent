package com.haven.businessService.mapper;

import com.haven.businessService.entity.TFood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 食品表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@Mapper
public interface TFoodMapper extends BaseMapper<TFood> {

    List<String> selectStyleIdList(@Param("bsid") String id);
}
