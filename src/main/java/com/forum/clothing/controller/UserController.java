package com.forum.clothing.controller;

import com.forum.clothing.dto.InItUserInfoDto;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.service.UserService;
import com.forum.clothing.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户
 */
@Api(tags = "小程序用户")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * code 换取openId 并注册
     */
    @ApiOperation(value = "使用code换取用户信息")
    @GetMapping("getUserByWechatCode")
    public Result<AppUser> getUserByWechatCode(String code, String iv, String encryptedData) throws Exception {
        return userService.getUserByWechatCode(code, encryptedData, iv);
    }

    /**
     * code 换取openId 并注册
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("getUserByOpenId")
    public Result<AppUser> getUserByOpenId(String openid) {
        return userService.getUserByOpenId(openid);
    }

    /**
     * code 换取openId 并注册
     */
    @ApiOperation(value = "使用code换取用户手机号")
    @GetMapping("getUserPhoneByWechatCode")
    public Result<String> getUserPhoneByWechatCode(String code){
        return userService.getUserPhoneByWechatCode(code);
    }

    /**
     * app 注册，补充更新用户信息
     */
    @ApiOperation(value = "注册提交")
    @PostMapping("initUserInfo")
    public Result<AppUser> initUserInfo(@Valid InItUserInfoDto inItUserInfoDto){
        return userService.initUserInfo(inItUserInfoDto);
    }

    /**
     * app 我的
     */
    @ApiOperation(value = "小程序 我的")
    @GetMapping("info")
    public Result<AppUser> getUserInfo(String openId){
        return userService.getUserInfo(openId);
    }

}
