package com.forum.clothing.controller;

import com.forum.clothing.dto.InItUserInfoDto;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.service.UserService;
import com.forum.clothing.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * code 换取openId 并注册
     */
    @GetMapping("getUserByWechatCode")
    public Result<AppUser> getUserByWechatCode(String code){
        return userService.getUserByWechatCode(code);
    }

    /**
     * app 注册，补充更新用户信息
     */
    @PostMapping("initUserInfo")
    public Result<AppUser> initUserInfo(@RequestBody @Valid InItUserInfoDto inItUserInfoDto){
        return userService.initUserInfo(inItUserInfoDto);
    }

    /**
     * app 我的
     */
    @PostMapping("info")
    public Result<AppUser> getUserInfo(String openId){
        return userService.getUserInfo(openId);
    }

}
