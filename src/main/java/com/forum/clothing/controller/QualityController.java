package com.forum.clothing.controller;

import com.forum.clothing.dto.QualityPublishDto;
import com.forum.clothing.service.QualityService;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供需
 * @author zb
 */
@Api("货品接口")
@RestController
@RequestMapping("/v1/quality")
public class QualityController {

    @Autowired
    private QualityService qualityService;

    /**
     * app 发布
     */
    @ApiOperation("发布货品")
    @PostMapping("/publish")
    public Result<?> publish(QualityPublishDto qualityPublishDto) {
        try {
            qualityService.save(qualityPublishDto);
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
        return Results.success();
    }

    /**
     * 管理员 内容审核
     */
    @ApiOperation("管理员 内容审核 暂时不用")
    @PostMapping("/examine")
    public Result<?> examine(Integer qualityId, Integer adminId) {
        try {
            qualityService.examine(qualityId, adminId);
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
        return Results.success();
    }

    /**
     * 内容下架
     */
    @ApiOperation("管理员 内容下架 暂时不用")
    @PostMapping("/offline")
    public Result<?> offline(Integer qualityId, Integer appUserId) {
        try {
            qualityService.offline(qualityId, appUserId);
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
        return Results.success();
    }

    /**
     * 内容删除
     */
    @ApiOperation("管理员 内容删除 暂时不用")
    @PostMapping("/delete")
    public Result<?> delete(Integer qualityId, Integer appUserId) {
        try {
            qualityService.delete(qualityId, appUserId);
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
        return Results.success();
    }


    /**
     * 列表
     * @param pageType 0首页 1收藏 2我的发布
     */
    @ApiOperation("列表")
    @PostMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "10") Integer size,
                          String type,
                          Byte qualityType,
                          Integer pageType,
                          String openid
    ) {
        try {
            return Results.success(qualityService.list(page, size, type, qualityType, pageType, openid));
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
    }

    /**
     * 收藏
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("/collect")
    public Result<?> collect(Integer id, Integer userId){
        return qualityService.collect(id, userId);
    }

}
