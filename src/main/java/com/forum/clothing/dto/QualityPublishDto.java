package com.forum.clothing.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhengqiang.shi
 * @Date 2023/8/26 13:52
 */
@Data
public class QualityPublishDto {

    /**
     * 用户ID
     */
    private Integer appUserId;

    /**
     * 内容ID
     */
    private Integer id;

    /**
     * 类别
     */
    private String type;

    /**
     * 供需量
     */
    private BigDecimal demand;

    /**
     * 产品指标，质量百分比，Json格式
     */
    private String qualityPer;

    /**
     * 所在地
     */
    private String address;
}
