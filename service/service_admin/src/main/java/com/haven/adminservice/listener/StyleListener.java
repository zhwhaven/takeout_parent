package com.haven.adminservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haven.adminservice.entity.TFoodStyle;
import com.haven.adminservice.service.TFoodStyleService;
import com.haven.adminservice.vo.StyleDemo;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StyleListener extends AnalysisEventListener<StyleDemo> {

    private TFoodStyleService styleService;
    public  StyleListener(){}
    public  StyleListener(TFoodStyleService styleService){
        this.styleService=styleService;
    }
    @Override
    public void invoke(StyleDemo styleDemo, AnalysisContext analysisContext) {
        String oneStyle = styleDemo.getOneStyle();
        TFoodStyle foodStyle=exitOneStyle(oneStyle);
        if(foodStyle==null){
            foodStyle=new TFoodStyle();
            styleService.save(foodStyle.setTitle(oneStyle));
        }
        TFoodStyle foodStyle2=exitTwoStyle(foodStyle.getId(),styleDemo.getTwoStyle());
        if(foodStyle2==null){
            foodStyle2=new TFoodStyle();
            foodStyle2.setParentId(foodStyle.getId());
            foodStyle2.setTitle(styleDemo.getTwoStyle());
             styleService.save(foodStyle2);
        }
    }

    private TFoodStyle exitTwoStyle(String parent_id, String twoStyle) {
        QueryWrapper<TFoodStyle> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",parent_id);
        wrapper.eq("title",twoStyle);
        TFoodStyle one = styleService.getOne(wrapper);
        return one;
    }

    private TFoodStyle exitOneStyle(String oneStyle) {
        QueryWrapper<TFoodStyle> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        wrapper.eq("title",oneStyle);
        TFoodStyle foodStyle = styleService.getOne(wrapper);
        return foodStyle;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
