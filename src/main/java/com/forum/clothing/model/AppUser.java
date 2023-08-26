package com.forum.clothing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_user")
public class AppUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 用户号
     */
    private String userCode;

    /**
     * 用户类型：1供货商2经销商
     */
    private Byte userType;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 0未认证 1已认证
     */
    private Boolean auth;

    /**
     * 认证金额
     */
    private Long authAmount;

    /**
     * 邀请人ID
     */
    private Integer inviteUserId;

    /**
     * openId
     */
    private String openId;

    /**
     *
     */
    private Date createTime;

    private Date updateTime;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 认证图片 逗号割
     */
    private String sitePic;

}