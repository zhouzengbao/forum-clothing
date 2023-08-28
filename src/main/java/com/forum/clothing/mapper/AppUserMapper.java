package com.forum.clothing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forum.clothing.model.AppUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {
    AppUser selectByOpenId(String openId);

}