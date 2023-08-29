package com.forum.clothing.controller;

import com.forum.clothing.dto.QualityPublishDto;
import com.forum.clothing.service.QualityService;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 供需
 */
@RestController
@RequestMapping("/quality")
public class QualityController {

    @Autowired
    private QualityService qualityService;

    /**
     * app 发布
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody QualityPublishDto qualityPublishDto) {
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
    @PostMapping("/examine")
    public Result examine(Integer qualityId, Integer adminId) {
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
    @PostMapping("/offline")
    public Result offline(Integer qualityId, Integer appUserId) {
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
    @PostMapping("/delete")
    public Result delete(Integer qualityId, Integer appUserId) {
        try {
            qualityService.delete(qualityId, appUserId);
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
        return Results.success();
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result list(Integer current,
                       Integer size,
                       String type,
                       Byte qualityType) {
        try {
            return Results.success(qualityService.list(current, size, type, qualityType));
        } catch (Exception e) {
            return Results.failure(e.getMessage());
        }
    }

}
