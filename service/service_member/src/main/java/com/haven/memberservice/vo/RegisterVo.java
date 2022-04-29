package com.haven.memberservice.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {

    String nickname;
    String mobile;
    String password;
    String code;
}
