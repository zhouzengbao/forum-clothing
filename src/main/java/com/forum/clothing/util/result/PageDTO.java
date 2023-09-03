package com.forum.clothing.util.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author zb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {

    /**
     * 当前满足条件总行数
     */
    private Long total;

    /**
     * 当前分页总页数
     */
    private Long pages;

    /**
     * 当前页
     */
    private Long num;

    /**
     * 获取每页显示条数
     */
    private Long size;

    /**
     * 列表
     */
    private List<T> list;
}