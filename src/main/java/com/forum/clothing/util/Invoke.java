package com.forum.clothing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class Invoke {
    static Logger logger = LoggerFactory.getLogger(Invoke.class);

    public static String get(String url, String... data) {
        url = MessageFormat.format(url, data);
        logger.info("请求微信接口：{}", url);
        return HttpClient.get(url);
    }

    public static String post(String url, String json, String... urlData) {
        url = MessageFormat.format(url, urlData);
        logger.info("请求微信接口：{}", url);
        logger.info("请求微信接口数据：{}", json);
        return HttpClient.post(url, json);
    }

    public static byte[] getByte(String url, String... urlData) {
        url = MessageFormat.format(url, urlData);
        logger.info("请求微信接口：{}", url);
        return HttpClient.getBytes(url);
    }

}