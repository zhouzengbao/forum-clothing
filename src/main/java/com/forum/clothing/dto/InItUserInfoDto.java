package com.forum.clothing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "姓名不能为空")
    private String userName;
    /**
     * 身份证号
     */
    @NotNull(message = "身份证号不能为空")
    private String idNum;
    /**
     * 用户类型
     */
    @NotNull(message = "请选择用户类型")
    private Integer userType;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String telephone;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String code;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 图片
     */
    @Size(min = 3, max = 3, message = "请上传3张认证图片")
    private List<String> sitePic;

    @NotNull(message = "参数错误!")
    private String userId;
}
