package com.haven.adminservice.service;

import com.haven.adminservice.entity.TFoodStyle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haven.adminservice.vo.FirstStyleVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 食品种类表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
public interface TFoodStyleService extends IService<TFoodStyle> {

   void upload(MultipartFile file,TFoodStyleService foodStyleService);

   List<FirstStyleVo> selectAllStyle();
}
