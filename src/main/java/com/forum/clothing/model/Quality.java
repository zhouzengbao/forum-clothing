package com.forum.clothing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quality {
    private Integer id;

    private Integer appUserId;

    private String type;

    private BigDecimal demand;

    private BigDecimal qualityPer;

    private String address;

    private Boolean qualityType;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

}