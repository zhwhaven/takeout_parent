package com.haven.orderservice.mapper;

import com.haven.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

}
