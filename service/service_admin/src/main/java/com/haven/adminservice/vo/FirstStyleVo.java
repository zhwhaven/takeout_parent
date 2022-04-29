package com.haven.adminservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class FirstStyleVo {
    public String id;
    public String title;
    public List<SecondStyleVo> childern;
}
