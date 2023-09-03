package com.forum.clothing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forum.clothing.model.AppUser;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author zb
 */
@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {
    /**
     * openId查询用户
     * @param openId 微信openId
     * @return 用户
     */
    AppUser selectByOpenId(String openId);

}