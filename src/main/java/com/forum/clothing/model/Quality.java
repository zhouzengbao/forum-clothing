package com.forum.clothing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zb
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("quality")
public class Quality {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发布用户ID
     */
    private Integer appUserId;

    /**
     * 类别：0 夏装 1 冬装
     */
    private String type;

    /**
     * 需求量 单位（吨）
     */
    private BigDecimal demand;

    /**
     * 质量百分比  A:30,B:60,C:10
     */
    private String qualityPer;

    /**
     * 地址
     */
    private String address;

    /**
     * 供需关系 0 供货 1需求
     */
    private Byte qualityType;

    /**
     * '-1 删除 0 创建 1上架
     */
    private Byte status;

    /**
     * 浏览量
     */
    private Long pageView;

    private Date createTime;

    private Date updateTime;

}