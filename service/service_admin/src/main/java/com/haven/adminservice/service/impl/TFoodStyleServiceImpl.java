package com.haven.adminservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.adminservice.entity.TFoodStyle;
import com.haven.adminservice.listener.StyleListener;
import com.haven.adminservice.mapper.TFoodStyleMapper;
import com.haven.adminservice.service.TFoodStyleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haven.adminservice.vo.FirstStyleVo;
import com.haven.adminservice.vo.SecondStyleVo;
import com.haven.adminservice.vo.StyleDemo;
import com.haven.baseService.ExceptionHandler.GuliException;
import org.bouncycastle.crypto.signers.ISOTrailers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 食品种类表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@Service
public class TFoodStyleServiceImpl extends ServiceImpl<TFoodStyleMapper, TFoodStyle> implements TFoodStyleService {

    @Override
    public void upload(MultipartFile file,TFoodStyleService styleService) {

        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, StyleDemo.class,new StyleListener(styleService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20001,"添加课程分类失败");
        }
    }

    @Override
    public List<FirstStyleVo> selectAllStyle() {
        ArrayList<FirstStyleVo> firstStyleVos=new ArrayList<>();
        List<TFoodStyle> firstList = baseMapper.
                selectList(new QueryWrapper<TFoodStyle>().eq("parent_id", "0"));
        System.out.println(firstList);
        for (TFoodStyle first : firstList) {
            FirstStyleVo one=new FirstStyleVo();
            BeanUtils.copyProperties(first,one);
//            获取一级id，作为二级的父id
            String parentId = first.getId();
//            创建二级接受类集合
            ArrayList<SecondStyleVo> secondStyleVos=new ArrayList<>();

            QueryWrapper<TFoodStyle> wrapper=new QueryWrapper<>();
            wrapper.eq("parent_id",parentId);
            List<TFoodStyle> secondList = baseMapper.selectList(wrapper);
            for (TFoodStyle second : secondList) {
                SecondStyleVo secondStyleVo=new SecondStyleVo();
                BeanUtils.copyProperties(second,secondStyleVo);
                secondStyleVos.add(secondStyleVo);
            }
            one.setChildern(secondStyleVos);
            firstStyleVos.add(one);
        }

        return firstStyleVos;
    }
}
