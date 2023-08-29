package com.forum.clothing.controller;

import com.forum.clothing.model.AppUser;
import com.forum.clothing.service.UserService;
import com.forum.clothing.util.result.PageDTO;
import com.forum.clothing.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 超管
 */
@Api("超管")
@RestController
@RequestMapping("/v1/admin/user")
public class AdminUserController {

    @Resource
    private UserService userService;
    /**
     * 超管 用户分页列表
     */
    @ApiOperation("用户分页")
    @GetMapping("page")
    public PageDTO<?> getUserPage(@RequestParam("pageSize")Long pageSize, @RequestParam("page")Long page){
        return userService.getUserPage(pageSize, page);
    }


    /**
     * 超管 设置用户未已审核、取消审核
     */
    @ApiOperation("用户审核")
    @GetMapping("auth")
    public Result<?> auth(Long userId, Integer auth, Long authAmount, Long expireTime){
        return userService.auth(userId, auth, authAmount, expireTime);
    }

}
