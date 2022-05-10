package com.haven.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haven.orderservice.entity.TComment;
import com.haven.orderservice.service.TCommentService;
import com.haven.orderservice.service.TOrderService;
import com.haven.orderservice.vo.SelectCommentVo;
import com.haven.utilscommon.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-25
 */
@Api(description = "评论模块")
@RestController
@RequestMapping("/orderservice/t-comment")
public class TCommentController {

    @Autowired
    TCommentService commentService;

    @Autowired
    TOrderService orderService;

    @ApiOperation("添加评论")
    @PostMapping("/addComment")
    public R addComment(@RequestBody TComment comment){
        commentService.addComment(comment);
        return R.ok();
    }

    @ApiOperation("删除评论")
    @PostMapping("/deleteComment/{id}")
    public R deleteComment(@PathVariable String id){
        boolean b = commentService.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("根据商家id查询所有评论")
    @PostMapping("/selectCommentList/{businessId}")
    public R selectCommentList(@PathVariable String businessId){
        QueryWrapper<TComment> wrapper=new QueryWrapper<>();
        wrapper.eq("business_id", businessId);
        List<TComment> commentList = commentService.list(wrapper);
        return R.ok().data("commentList",commentList);
    }

//selectCommentPage

    @ApiOperation("分页查询评论")
    @PostMapping("selectCommentPage/{current}/{limit}")
    public R selectCommentPage(@RequestBody SelectCommentVo selectCommentVo,
                        @PathVariable("current") int current,
                        @PathVariable("limit") int limit){
        Page<TComment> page=new Page<>(current,limit);
        QueryWrapper<TComment> queryWrapper=new QueryWrapper<>();
        if(selectCommentVo!=null){
            if(selectCommentVo.getId()!=null&& StringUtils.isEmpty(selectCommentVo.getId())){
                queryWrapper.eq("id",selectCommentVo.getId());
            }
            if(selectCommentVo.getOrderId()!=null&& StringUtils.isEmpty(selectCommentVo.getOrderId())){
                queryWrapper.eq("order_id",selectCommentVo.getOrderId());
            }

        }
        commentService.page(page,queryWrapper);
        List<TComment> commentList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("commentList",commentList).data("total",total);
    }





}

