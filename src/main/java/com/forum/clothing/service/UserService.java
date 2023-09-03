package com.forum.clothing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.clothing.config.SystemConfig;
import com.forum.clothing.dto.InItUserInfoDto;
import com.forum.clothing.dto.WechatLoginDto;
import com.forum.clothing.mapper.AppUserMapper;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.util.AesCbcUtil;
import com.forum.clothing.util.WeChatUtil;
import com.forum.clothing.util.result.PageDTO;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * @author zb
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private AppUserMapper appUserMapper;

    private static final Integer insertIdSize = 4;

    public Result<AppUser> getUserByWechatCode(String code, String encryptedData, String iv) throws Exception {

        WechatLoginDto login = WeChatUtil.login(code, SystemConfig.appId, SystemConfig.secret);
        if (Objects.isNull(login)){
            return Results.failure("授权失败!");
        }
        String openid = login.getOpenid();
        String sessionKey = login.getSession_key();

        AppUser appUser = appUserMapper.selectByOpenId(openid);
        if (Objects.isNull(appUser)){

            String userInfoBody = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(userInfoBody);

            appUser = AppUser.builder()
                    .inviteUserId(StringUtils.EMPTY)
                    .idNum(StringUtils.EMPTY)
                    .userCode(StringUtils.EMPTY)
                    .userType(0)
                    .authAmount(0L)
                    .userName(StringUtils.EMPTY)
                    .openId(openid)
                    .sessionKey(sessionKey)
                    .auth(0)
                    .nickName(jsonObject.getString("nickName"))
                    .expireTime(0L)
                    .sitePic(StringUtils.EMPTY)
                    .avatarUrl(jsonObject.getString("avatarUrl"))
                    .telephone(StringUtils.EMPTY)
                    .createTime(System.currentTimeMillis())
                    .updateTime(System.currentTimeMillis())
                    .build();
            appUserMapper.insert(appUser);

            StringBuilder id = new StringBuilder(String.valueOf(appUser.getId()));
            String yyMmDd = DateTimeFormatter.ofPattern("yyMMdd").format(LocalDate.now());
            while (id.length() < insertIdSize){
                id.insert(0, "0");
            }

            String userCode = yyMmDd + id;
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

        if (!isValidId(inItUserInfoDto.getIdNum())){
            return Results.failure("身份证无效");
        }
        appUser.setUserName(inItUserInfoDto.getUserName());
        appUser.setInviteUserId(inItUserInfoDto.getInviteCode());
        appUser.setUserType(inItUserInfoDto.getUserType());
        appUser.setIdNum(inItUserInfoDto.getIdNum());
        appUser.setTelephone(inItUserInfoDto.getTelephone());
        appUser.setUpdateTime(System.currentTimeMillis());
        appUser.setSitePic(inItUserInfoDto.getSitePic());
        appUserMapper.updateById(appUser);

        return Results.success(appUser);
    }

    public static boolean isValidId(String id) {
        //身份证号码的正则表达式
        String pattern = "^\\d{17}(\\d|x|X)$";
        if (!id.matches(pattern)) {
            return false;
        }
        //省份代码
        String[] provinceCodes = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"};
        List<String> provinceList = Arrays.asList(provinceCodes);
        if (!provinceList.contains(id.substring(0, 2))) {
            return false;
        }
        //生日校验
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(id.substring(6, 14));
        } catch (ParseException e) {
            return false;
        }
        //校验码验证
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * Integer.parseInt(id.substring(i, i + 1));
        }
        int mod = sum % 11;
        String[] checkCodes = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String checkCode = checkCodes[mod];
        return checkCode.equalsIgnoreCase(id.substring(17));
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
        IPage<AppUser> myPage = appUserMapper.selectPage(iPage, wrapper);
        return new PageDTO<>(
                myPage.getTotal(),
                myPage.getPages(),
                myPage.getCurrent(),
                myPage.getSize(),
                myPage.getRecords());
    }

    public Result<?> auth(Long userId, Integer auth, Long authAmount, Long expireTime) {

        if (checkAuth(userId, auth)){
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

    private static boolean checkAuth(Long userId, Integer auth) {
        return Objects.isNull(userId) || Objects.isNull(auth) || (Objects.equals(0, auth) && Objects.equals(1, auth));
    }

    public Result<String> getUserPhoneByWechatCode(String code) {
        return Results.success(WeChatUtil.getPhone(code));
    }

    public Result<AppUser> getUserByOpenId(String openid) {
        return Results.success(appUserMapper.selectByOpenId(openid));
    }
}
