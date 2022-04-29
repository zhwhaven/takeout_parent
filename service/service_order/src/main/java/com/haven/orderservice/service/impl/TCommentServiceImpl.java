package com.haven.orderservice.service.impl;

import com.haven.orderservice.entity.TComment;
import com.haven.orderservice.mapper.TCommentMapper;
import com.haven.orderservice.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
