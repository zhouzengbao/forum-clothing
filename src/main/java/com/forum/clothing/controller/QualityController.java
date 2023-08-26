package com.forum.clothing.controller;

import com.forum.clothing.dto.QualityPublishDto;
import com.forum.clothing.service.QualityService;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * app 查询  推荐 、收藏
     */

    /**
     * 超管 设置用户未已审核、取消审核
     */


}
