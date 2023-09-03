package com.forum.clothing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户注册信息
 */
@ApiModel(value = "Blog", description = "用户注册传入对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InItUserInfoDto {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空")
    private String userName;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @NotNull(message = "身份证号不能为空")
    private String idNum;
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型 1供货商2经销商")
    @NotNull(message = "请选择用户类型")
    private Integer userType;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号 由小程序注册获得")
    @NotNull(message = "手机号不能为空")
    private String telephone;
    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
    /**
     * 图片
     */
    @ApiModelProperty(value = "认证图片")
    private String sitePic;

    @ApiModelProperty(value = "用户ID，使用code换取的用户信息中的ID")
    @NotNull(message = "参数错误!")
    private String userId;
}
