package com.forum.clothing.task;

import com.forum.clothing.config.SystemConfig;
import com.forum.clothing.util.WeChatUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author zb
 */
@Component
public class RunTask {

    @Scheduled(fixedDelay = 7190000)
    public void refreshAccessToken(){

        SystemConfig.accessToken = WeChatUtil.getAccessToken(SystemConfig.appId, SystemConfig.secret);

    }
}
