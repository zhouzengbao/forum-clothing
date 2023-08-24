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

    private String nickName;

    private String telephone;

    private String userCode;

    private Boolean userType;

    private String idNum;

    private Boolean auth;

    private Long authAmount;

    private Integer inviteUserId;

    private String openId;

    private Date createTime;

    private Date updateTime;

    private String avatarUrl;

    private String sitePic;

}