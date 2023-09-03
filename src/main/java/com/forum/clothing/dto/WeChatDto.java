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
public class WeChatDto {
    private String nickName;
    private String avatarUrl;
    /**
     * 0	未知
     * 1	男性
     * 2	女性
     */
    private String gender;
    private String country;
    private String province;
    private String city;
    private String language;
}
