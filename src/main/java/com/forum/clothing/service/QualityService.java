package com.forum.clothing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.clothing.dto.QualityDetailDto;
import com.forum.clothing.dto.QualityPublishDto;
import com.forum.clothing.enums.AppUserTypeEnum;
import com.forum.clothing.enums.QualityQualityTypeEnum;
import com.forum.clothing.enums.QualityTypeEnum;
import com.forum.clothing.mapper.AppUserMapper;
import com.forum.clothing.mapper.QualityCollectMapper;
import com.forum.clothing.mapper.QualityMapper;
import com.forum.clothing.model.AppUser;
import com.forum.clothing.model.Quality;
import com.forum.clothing.model.QualityCollect;
import com.forum.clothing.util.result.PageDTO;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhengqiang.shi
 * 2023/8/26 13:38
 */
@Service
@Slf4j
public class QualityService {

    @Resource
    private QualityMapper qualityMapper;
    @Resource
    private AppUserMapper appUserMapper;
    @Resource
    private QualityCollectMapper qualityCollectMapper;

    /**
     * 保存,新增 or 更新
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(QualityPublishDto qualityPublishDto) {
        log.info("save quality,request param:{}", qualityPublishDto);
        String openid = qualityPublishDto.getOpenid();
        AppUser appUser = appUserMapper.selectByOpenId(openid);
        if (appUser == null || appUser.getAuth() == 0) {
            throw new IllegalArgumentException("用户不存在或未审核");
        }
        String qualityPer = qualityPublishDto.getQualityPer();
        JSONObject jsonObject = JSON.parseObject(qualityPer);
        Long a = jsonObject.getLong("a");
        Long b = jsonObject.getLong("b");
        Long c = jsonObject.getLong("c");
        if (a + b + c != 100L){
            throw new RuntimeException("综合指标必须是100%");
        }

        Quality quality = new Quality();
        BeanUtils.copyProperties(qualityPublishDto, quality);
        quality.setQualityType(appUser.getUserType().byteValue());
        quality.setStatus((byte) 0);
        quality.setAppUserId(appUser.getId());
        quality.setAppUserType(appUser.getUserType());
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
     *      openid 查询用户身份
     */
    public PageDTO<QualityDetailDto> list(Integer current, Integer size, String type, Byte qualityType, Integer pageType, String openid) {

        if (StringUtils.isEmpty(openid)) {
            return new PageDTO<>();
        }
        AppUser mainUser = appUserMapper.selectByOpenId(openid);
        if (Objects.isNull(mainUser)) {
            return new PageDTO<>();
        }

        IPage<Quality> page = new Page<>(current, size);
        LambdaQueryWrapper<Quality> lambdaQuery = Wrappers.lambdaQuery(Quality.class);

        switch (pageType) {
            case 0:
                // 首页
                AppUserTypeEnum userTypeEnum = AppUserTypeEnum.getByType(mainUser.getUserType());
                switch (Objects.requireNonNull(userTypeEnum)) {
                    case SUPPLIER:
                        // 原材料供应商和分拣场
                        lambdaQuery.in(Quality::getAppUserType, AppUserTypeEnum.SUPPLIER.getUserType(), AppUserTypeEnum.YARD.getUserType());
                    case YARD:
                        // 不限制
                        break;
                    case MERCHANT:
                        // 贸易商和分拣场
                        lambdaQuery.in(Quality::getAppUserType, AppUserTypeEnum.MERCHANT.getUserType(), AppUserTypeEnum.YARD.getUserType());
                        break;
                }
                break;
            case 1:
                // 我的收藏
                List<Integer> collectQualityIdList = qualityCollectMapper.selectList(Wrappers.lambdaQuery(QualityCollect.class).eq(QualityCollect::getAppUserId, mainUser.getId())).stream().map(QualityCollect::getQualityId).collect(Collectors.toList());
                lambdaQuery.in(Quality::getId, collectQualityIdList);
                break;
            case 2:
                // 我的发布
                lambdaQuery.eq(Quality::getAppUserId, mainUser.getId());
                break;
        }

        lambdaQuery.eq(StringUtils.isNotBlank(type), Quality::getType, type);
        lambdaQuery.eq(qualityType != null, Quality::getQualityType, qualityType);
        lambdaQuery.orderByDesc(Quality::getId);
        IPage<Quality> qualityIPage = qualityMapper.selectPage(page, lambdaQuery);
        List<QualityDetailDto> collect = null;
        if (qualityIPage.getTotal() > 0) {

            List<Integer> collectQualityIdList = qualityCollectMapper.selectList(Wrappers.lambdaQuery(QualityCollect.class).eq(QualityCollect::getAppUserId, mainUser.getId())).stream().map(QualityCollect::getQualityId).collect(Collectors.toList());
            boolean empty = collectQualityIdList.isEmpty();

            collect = qualityIPage.getRecords().stream().map(qp -> {
                QualityDetailDto qualityDetailDto = new QualityDetailDto();
                BeanUtils.copyProperties(qp, qualityDetailDto);
                // 用户信息
                AppUser appUser = appUserMapper.selectById(qp.getAppUserId());
                qualityDetailDto.setUserType(appUser.getUserType());
                qualityDetailDto.setUserName(appUser.getUserName());

                // 供需
                qualityDetailDto.setQualityTypeStr(QualityQualityTypeEnum.getQualityTypeStrByCode(qp.getQualityType()));
                //冬装夏装
                qualityDetailDto.setTypeStr(QualityTypeEnum.getTypeStrByCode(qp.getType()));

                AppUser currAppUser = appUserMapper.selectById(qp.getAppUserId());
                //用户类型
                qualityDetailDto.setUserTypeStr(AppUserTypeEnum.getUserTypeStr(currAppUser.getUserType()));
                qualityDetailDto.setCollect(0);
                if (!empty) {
                    qualityDetailDto.setCollect(collectQualityIdList.contains(qp.getId()) ? 1 : 0);
                }

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

    public Result<?> collect(Integer id, Integer userId) {
        LambdaQueryWrapper<QualityCollect> eqWrapper = Wrappers.lambdaQuery(QualityCollect.class).eq(QualityCollect::getQualityId, id).eq(QualityCollect::getAppUserId, userId);
        QualityCollect qualityCollect = qualityCollectMapper.selectOne(eqWrapper);
        if (Objects.isNull(qualityCollect)) {
            qualityCollectMapper.insert(QualityCollect.builder().qualityId(id).appUserId(userId).createTime(new Date()).build());
        } else {
            qualityCollectMapper.delete(eqWrapper);
        }
        return Results.success();
    }
}
