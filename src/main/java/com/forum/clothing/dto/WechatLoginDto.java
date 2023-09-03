package com.forum.clothing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author zb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatLoginDto {
    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
