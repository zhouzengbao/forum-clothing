package com.forum.clothing.controller;

import com.forum.clothing.service.UserService;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
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
    @RequestMapping("getAllUser")
    public Result<?> getAllUser(){
        return Results.success(userService.findALlUser());
    }
}
