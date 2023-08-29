package com.forum.clothing.util;

import com.forum.clothing.dto.WeChatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
public class OpenIdUtil {

    public static String getOpenId(String code, String appId, String appSecret) {
        // 根据code获取 access_token
        String accessTokenResult = Invoke.get(WpApiUrl.Web.LOGIN.url(), appId, appSecret, code);
        log.info("获取 openId 返回结果 -> {}", accessTokenResult);
        ResultHandler accessTokenResultHandler = ResultHandler.create(accessTokenResult);
        if (accessTokenResultHandler.isSuccess()) {
            return Optional.ofNullable(accessTokenResultHandler.get("openid")).map(String::valueOf).orElse(null);
        }
        throw new RuntimeException("获取openId授权异常");
    }
    public static WeChatDto getWechatDto(String code, String appId, String appSecret) {
        // 根据code获取 access_token
        String accessTokenResult = Invoke.get(WpApiUrl.Web.Web_Access_Token.url(), appId, appSecret, code);
        log.info("获取 openId 返回结果 -> {}", accessTokenResult);
        ResultHandler accessTokenResultHandler = ResultHandler.create(accessTokenResult);
        if (accessTokenResultHandler.isSuccess()) {
            return accessTokenResultHandler.getDto();
        }
        throw new RuntimeException("获取openId授权异常");
    }
}
