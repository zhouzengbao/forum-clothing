package com.forum.clothing.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.clothing.config.SystemConfig;
import com.forum.clothing.dto.InItUserInfoDto;
import com.forum.clothing.mapper.AppUserMapper;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.util.OpenIdUtil;
import com.forum.clothing.util.result.PageDTO;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Resource
    private AppUserMapper appUserMapper;

    public Result<AppUser> getUserByWechatCode(String code) {

        String openId = OpenIdUtil.getOpenId(code, SystemConfig.appId, SystemConfig.secret);
        if (Objects.isNull(openId)){
            return Results.failure("授权失败!");
        }
        AppUser appUser = appUserMapper.selectByOpenId(openId);
        if (Objects.isNull(appUser)){
            appUser = AppUser.builder()
                    .inviteUserId(StringUtils.EMPTY)
                    .idNum(StringUtils.EMPTY)
                    .userCode(StringUtils.EMPTY)
                    .userType(0)
                    .authAmount(0L)
                    .userName(StringUtils.EMPTY)
                    .openId(openId)
                    .auth(0)
                    .nickName(StringUtils.EMPTY)
                    .expireTime(0L)
                    .sitePic(StringUtils.EMPTY)
                    .avatarUrl(StringUtils.EMPTY)
                    .telephone(StringUtils.EMPTY)
                    .createTime(System.currentTimeMillis())
                    .updateTime(System.currentTimeMillis())
                    .build();
            appUserMapper.insert(appUser);

            StringBuilder id = new StringBuilder(String.valueOf(appUser.getId()));
            String yyMMdd = DateTimeFormatter.ofPattern("yyMMdd").format(LocalDate.now());
            while (id.length() < 4){
                id.insert(0, "0");
            }

            String userCode = yyMMdd + id;
            appUser.setUserCode(userCode);
            appUserMapper.updateById(appUser);

            log.info("创建用户 {}", JSON.toJSONString(appUser));
        }

        return Results.success(appUser);
    }

    public Result<AppUser> initUserInfo(InItUserInfoDto inItUserInfoDto) {
        String userId = inItUserInfoDto.getUserId();
        AppUser appUser = appUserMapper.selectById(userId);
        if (Objects.isNull(appUser)){
            return Results.failure("用户不存在!");
        }
        appUser.setUserName(inItUserInfoDto.getUserName());
        appUser.setInviteUserId(inItUserInfoDto.getInviteCode());
        appUser.setUserType(inItUserInfoDto.getUserType());
        appUser.setIdNum(inItUserInfoDto.getIdNum());
        appUser.setTelephone(inItUserInfoDto.getTelephone());
        appUser.setUpdateTime(System.currentTimeMillis());
        appUser.setSitePic(JSON.toJSONString(inItUserInfoDto.getSitePic()));
        appUserMapper.updateById(appUser);

        return Results.success(appUser);
    }

    public Result<AppUser> getUserInfo(String openId) {

        AppUser appUser = appUserMapper.selectByOpenId(openId);
        if (Objects.isNull(appUser)){
            return Results.failure("用户不存在!");
        }
        return Results.success(appUser);
    }

    public PageDTO<?> getUserPage(Long pageSize, Long page) {
        LambdaQueryWrapper<AppUser> wrapper = Wrappers.lambdaQuery(AppUser.class);
        IPage<AppUser> iPage = new Page<>(page,pageSize);
        wrapper
                .orderByDesc(AppUser::getId);
        IPage<AppUser> logIPage = appUserMapper.selectPage(iPage, wrapper);
        return new PageDTO<>(
                logIPage.getTotal(),
                logIPage.getPages(),
                logIPage.getCurrent(),
                logIPage.getSize(),
                logIPage.getRecords());
    }

    public Result<?> auth(Long userId, Integer auth, Long authAmount, Long expireTime) {

        if (Objects.isNull(userId) || Objects.isNull(auth) || (Objects.equals(0, auth) && Objects.equals(1, auth))){
            return Results.failure("参数错误!");
        }

        AppUser appUser = appUserMapper.selectById(userId);
        if (Objects.isNull(appUser)){
            return Results.failure("用户不存在!");
        }

        long l = System.currentTimeMillis();
        appUser.setAuth(auth);

        if (Objects.equals(1, auth)){

            appUser.setAuthAmount(authAmount);
            appUser.setExpireTime(expireTime);
            appUser.setUpdateTime(l);

        }else {

            appUser.setExpireTime(l);
            appUser.setUpdateTime(l);

        }

        appUserMapper.updateById(appUser);

        return Results.success();
    }
}
