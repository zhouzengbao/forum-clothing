package com.forum.clothing.dto;

import com.forum.clothing.model.Quality;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhengqiang.shi
 * 2023/8/26 21:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QualityDetailDto extends Quality{
    private Integer userType;
    private String userName;
    /**
     * 供需字典翻译
     */
    private String qualityTypeStr;
    /**
     * 发布人员类型翻译
     */
    private String userTypeStr;
    /**
     * 冬装夏装类型翻译
     */
    private String typeStr;

    /**
     * 关注1  没关注0
     */
    private Integer collect;

}
