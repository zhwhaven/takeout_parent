package com.haven.adminservice.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StyleDemo {
    @ExcelProperty(value = "一级分类",index = 0)
    private String oneStyle;
    @ExcelProperty(value = "二级分类",index = 1)
    private String twoStyle;
}
