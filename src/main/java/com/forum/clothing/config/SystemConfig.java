package com.forum.clothing.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zb
 */
@Data
@Component
public class SystemConfig {


    /**
     * 微信
     */
    public static String appId;
    public static String secret;
    public static String accessToken;

    /**
     * 发短信 配置
     */

    @Value("${wechat.appId}")
    public void setAppId(String appId){
        SystemConfig.appId = appId;
    }

    @Value("${wechat.secret}")
    public void setSecret(String secret){
        SystemConfig.secret = secret;
    }

}
