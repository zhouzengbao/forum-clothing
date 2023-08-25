package com.forum.clothing.controller;

import com.forum.clothing.model.AppUser;
import com.forum.clothing.service.UserService;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * demo
     * @return
     */
    @RequestMapping("getAllUser")
    public Result<?> getAllUser(){
        return Results.success(userService.findALlUser());
    }

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
    @GetMapping("initUserInfo")
    public Result<AppUser> initUserInfo(String code){
        return userService.getUserByWechatCode(code);
    }

    /**
     * app 微信公众号关注
     */

    /**
     * app 我的
     */

    /**
     * 超管 用户分页列表
     */

    /**
     * 超管 设置用户未已审核、取消审核
     */


}
