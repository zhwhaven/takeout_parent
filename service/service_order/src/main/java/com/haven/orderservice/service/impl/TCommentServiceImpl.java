package com.haven.orderservice.service.impl;

import com.haven.orderservice.entity.TComment;
import com.haven.orderservice.entity.TOrder;
import com.haven.orderservice.mapper.TCommentMapper;
import com.haven.orderservice.mapper.TOrderMapper;
import com.haven.orderservice.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {

    @Autowired
    TOrderMapper orderMapper;

    @Override
    public void addComment(TComment comment) {
//        添加评论
        int insert = baseMapper.insert(comment);
        if(insert==1){
//         评论成功由6
            String orderId = comment.getOrderId();
            TOrder order = orderMapper.selectById(orderId);
            order.setOrderStatus(10);
            orderMapper.updateById(order);
        }
    }
}
