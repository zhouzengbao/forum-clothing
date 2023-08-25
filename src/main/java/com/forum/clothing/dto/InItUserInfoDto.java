package com.forum.clothing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户注册信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InItUserInfoDto {

    /**
     * 姓名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idNum;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 图片
     */
    private List<String> sitePic;
}
