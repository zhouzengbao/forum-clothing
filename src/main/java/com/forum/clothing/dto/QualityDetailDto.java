package com.forum.clothing.dto;

import com.forum.clothing.model.Quality;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhengqiang.shi
 * @date 2023/8/26 21:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QualityDetailDto extends Quality{
    private Integer userType;
    private String userName;

}
