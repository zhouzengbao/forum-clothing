package com.forum.clothing.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forum.clothing.config.SystemConfig;
import com.forum.clothing.dto.WeChatDto;
import com.forum.clothing.dto.WechatLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class WeChatUtil {

    public static String getAccessToken(String appId, String appSecret){
        String accessTokenResult = Invoke.get(WpApiUrl.Core.Access_Token.url(), appId, appSecret);
        log.info("获取 access token 返回结果 -> {}", accessTokenResult);
        JSONObject jsonObject = JSON.parseObject(accessTokenResult);
        return jsonObject.getString("access_token");
    }

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


    public static WechatLoginDto login(String code, String appId, String appSecret) {
        // 根据code获取 access_token
        String accessTokenResult = Invoke.get(WpApiUrl.Web.LOGIN.url(), appId, appSecret, code);
        log.info("获取 openId 返回结果 -> {}", accessTokenResult);
        ResultHandler accessTokenResultHandler = ResultHandler.create(accessTokenResult);
        if (accessTokenResultHandler.isSuccess()) {
            return JSON.parseObject(accessTokenResult, WechatLoginDto.class);
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

    public static String getPhone(String code){

        if (StringUtils.isEmpty(SystemConfig.accessToken)){
            SystemConfig.accessToken = getAccessToken(SystemConfig.appId, SystemConfig.secret);
        }

        String url = Invoke.get(WpApiUrl.Core.USER_PHONE.url(), SystemConfig.accessToken);

        Map<String, String> body = new HashMap<>(1);
        body.put("code", code);
        String post = Invoke.post(url, JSON.toJSONString(body));

        ResultHandler phoneBody = ResultHandler.create(post);
        if (phoneBody.isSuccess()) {
            return phoneBody.getJSONObject("phone_info").getString("phoneNumber");
        }
        throw new RuntimeException("获取手机号异常!");
    }
}
