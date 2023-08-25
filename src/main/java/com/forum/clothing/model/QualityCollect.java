package com.forum.clothing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("quality")
public class QualityCollect {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer appUserId;

    private Integer qualityId;

    private Date createTime;

}