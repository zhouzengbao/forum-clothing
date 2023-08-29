package com.forum.clothing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhengqiang.shi
 * @Date 2023/8/26 13:52
 */
@Data
@ApiModel("货品类")
public class QualityPublishDto {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer appUserId;

    /**
     * 内容ID
     */
    @ApiModelProperty("本条主键")
    private Integer id;

    /**
     * 类别
     */
    @ApiModelProperty("类别 冬装|夏装")
    private String type;

    /**
     * 供需量
     */
    @ApiModelProperty("供需量")
    private BigDecimal demand;

    /**
     * 产品指标，质量百分比，Json格式
     */
    @ApiModelProperty("产品指标，质量百分比，Json格式 {'A':60,'B':10,'C':30}")
    private String qualityPer;

    /**
     * 所在地
     */
    @ApiModelProperty("所在地")
    private String address;
}
