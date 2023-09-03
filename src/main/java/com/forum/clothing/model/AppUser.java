package com.forum.clothing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zb
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_user")
@Api("用户基础类")
public class AppUser {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户主键")
    private Integer id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String telephone;

    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号")
    private String userCode;

    /**
     * 用户类型：1供货商2经销商
     */
    @ApiModelProperty(value = "用户类型0原材料供货商 2分拣场 3贸易商")
    private Integer userType;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 0未认证 1已认证
     */
    @ApiModelProperty(value = "认证状态0未认证 1已认证")
    private Integer auth;

    /**
     * 认证金额
     */
    @ApiModelProperty(value = "认证金额")
    private Long authAmount;

    /**
     * 邀请人ID
     */
    @ApiModelProperty(value = "邀请人code")
    private String inviteUserId;

    /**
     * openId
     */
    @ApiModelProperty(value = "openId")
    private String openId;

    /**
     * sessionKey
     */
    @ApiModelProperty(value = "sessionKey")
    private String sessionKey;

    /**
     *
     */
    @ApiModelProperty(value = "审核过期时间")
    private Long expireTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "最后更新时间")
    private Long updateTime;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    /**
     * 认证图片 逗号割
     */
    @ApiModelProperty(value = "认证图片")
    private String sitePic;

}