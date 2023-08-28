package com.forum.clothing.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class SystemConfig {


    /**
     * 微信
     */
    public static String appId;
    public static String secret;

    /**
     * aliyun 账号
     */
    public static String accessKeyId;
    public static String accessKeySecret;

    /**
     * aliyun  OSS 配置 内网地址
     */
    public static String endpoint;
    public static String bucketName;

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

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessId){
        SystemConfig.accessKeyId = accessId;
    }
    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret){
        SystemConfig.accessKeySecret = accessKeySecret;
    }
    @Value("${oss.bucketName}")
    public void setBucketName(String bucketName){
        SystemConfig.bucketName = bucketName;
    }
    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint){
        SystemConfig.endpoint = endpoint;
    }



}
