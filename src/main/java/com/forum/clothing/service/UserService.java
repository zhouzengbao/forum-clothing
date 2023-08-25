package com.forum.clothing.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.clothing.mapper.AppUserMapper;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.util.result.PageDTO;
import com.forum.clothing.util.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private AppUserMapper appUserMapper;


    public PageDTO<?> findALlUser() {
        LambdaQueryWrapper<AppUser> wrapper = Wrappers.lambdaQuery(AppUser.class);
        IPage<AppUser> page = new Page<>(1,20);
        wrapper
                .orderByDesc(AppUser::getId);
        IPage<AppUser> logIPage = appUserMapper.selectPage(page,wrapper);
        PageDTO<AppUser> result = new PageDTO<>(
                logIPage.getTotal(),
                logIPage.getPages(),
                logIPage.getCurrent(),
                logIPage.getSize(),
                logIPage.getRecords());
        return result;
    }

    public Result<AppUser> getUserByWechatCode(String code) {
        return null;
    }
}
