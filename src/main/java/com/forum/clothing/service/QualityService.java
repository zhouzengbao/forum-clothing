package com.forum.clothing.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.clothing.dto.QualityDetailDto;
import com.forum.clothing.dto.QualityPublishDto;
import com.forum.clothing.mapper.AppUserMapper;
import com.forum.clothing.mapper.QualityMapper;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.model.Quality;
import com.forum.clothing.util.result.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengqiang.shi
 * 2023/8/26 13:38
 */
@Service
@Slf4j
public class QualityService {

    @Autowired
    private QualityMapper qualityMapper;
    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 保存,新增 or 更新
     */
    @Transactional
    public void save(QualityPublishDto qualityPublishDto) {
        log.info("save quality,request param:{}", qualityPublishDto);
        String openid = qualityPublishDto.getOpenid();
        AppUser appUser = appUserMapper.selectByOpenId(openid);
        if (appUser == null || appUser.getAuth() == 0) {
            throw new IllegalArgumentException("用户不存在或未审核");
        }
        Quality quality = new Quality();
        BeanUtils.copyProperties(qualityPublishDto, quality);
        quality.setQualityType(appUser.getUserType().byteValue());
        quality.setStatus((byte) 0);
        quality.setAppUserId(appUser.getId());
        int result;
        if (quality.getId() != null && quality.getId() != 0) {
            // 下线才能更新
            Quality oldQuality = qualityMapper.selectById(qualityPublishDto.getId());
            if (oldQuality.getStatus() != 0) {
                throw new IllegalArgumentException("请将内容下线后进行更新");
            }
            result = qualityMapper.updateById(quality);
        } else {
            result = qualityMapper.insert(quality);
        }
        if (result != 1) {
            throw new RuntimeException("系统异常");
        }
    }

    /**
     * 审核
     */
    public void examine(Integer qualityId, Integer adminId) {
        log.info("examine,qualityId:{},adminId:{}", qualityId, adminId);
        Quality quality = qualityMapper.selectById(qualityId);
        if (quality == null) {
            throw new IllegalArgumentException("内容不存在，qualityId:" + qualityId);
        }
        if (quality.getStatus() != 0) {
            return;
        }
        quality.setStatus((byte) 1);
        qualityMapper.updateById(quality);
    }

    /**
     * 下线
     */
    public void offline(Integer qualityId, Integer appUserId) {
        log.info("offline,qualityId:{},appUserId:{}", qualityId, appUserId);
        LambdaQueryWrapper<Quality> lambdaQuery = Wrappers.lambdaQuery(Quality.class);
        lambdaQuery.eq(Quality::getId, qualityId);
        lambdaQuery.eq(Quality::getAppUserId, appUserId);
        lambdaQuery.eq(Quality::getStatus, 1);
        Quality quality = qualityMapper.selectOne(lambdaQuery);
        if (quality == null) {
            throw new IllegalArgumentException("内容不存在，qualityId:" + qualityId);
        }
        quality.setStatus((byte) 0);
        qualityMapper.updateById(quality);
    }

    /**
     * 删除
     */
    public void delete(Integer qualityId, Integer appUserId) {
        log.info("delete,qualityId:{},appUserId:{}", qualityId, appUserId);
        LambdaQueryWrapper<Quality> lambdaQuery = Wrappers.lambdaQuery(Quality.class);
        lambdaQuery.eq(Quality::getId, qualityId);
        lambdaQuery.eq(Quality::getAppUserId, appUserId);
        // 下线的内容才能删除
        lambdaQuery.eq(Quality::getStatus, 0);
        Quality quality = qualityMapper.selectOne(lambdaQuery);
        if (quality == null) {
            throw new IllegalArgumentException("内容不存在，qualityId:" + qualityId);
        }
        quality.setStatus((byte) -1);
        qualityMapper.updateById(quality);
    }

    /**
     * 列表
     * todo 新增接口参数 未实现
     *      pageType 0首页 1收藏 2我的发布
     *      openid 查询用户身份
     */
    public PageDTO<QualityDetailDto> list(Integer current, Integer size, String type, Byte qualityType, Integer pageType, String openid) {

        IPage<Quality> page = new Page<>(current, size);

        LambdaQueryWrapper<Quality> lambdaQuery = Wrappers.lambdaQuery(Quality.class);
        lambdaQuery.eq(StringUtils.isNotBlank(type),Quality::getType, type);
        lambdaQuery.eq(qualityType != null, Quality::getQualityType, qualityType);

        IPage<Quality> qualityIPage = qualityMapper.selectPage(page, lambdaQuery);
        List<QualityDetailDto> collect = null;
        if (qualityIPage.getRecords().size() > 0) {
            collect = qualityIPage.getRecords().stream().map(qp -> {
                QualityDetailDto qualityDetailDto = new QualityDetailDto();
                BeanUtils.copyProperties(qp, qualityDetailDto);
                // 用户信息
                AppUser appUser = appUserMapper.selectById(qp.getAppUserId());
                qualityDetailDto.setUserType(appUser.getUserType());
                qualityDetailDto.setUserName(appUser.getUserName());

                return qualityDetailDto;
            }).collect(Collectors.toList());
        }

        return new PageDTO<>(
                qualityIPage.getTotal(),
                qualityIPage.getPages(),
                qualityIPage.getCurrent(),
                qualityIPage.getSize(),
                collect);

    }

}
