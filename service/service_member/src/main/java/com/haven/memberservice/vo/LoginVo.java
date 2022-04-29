package com.haven.memberservice.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    String mobile;
    String password;
}
