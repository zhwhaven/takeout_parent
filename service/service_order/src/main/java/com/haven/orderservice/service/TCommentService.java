package com.haven.orderservice.service;

import com.haven.orderservice.entity.TComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
public interface TCommentService extends IService<TComment> {

    void addComment(TComment comment);
}
