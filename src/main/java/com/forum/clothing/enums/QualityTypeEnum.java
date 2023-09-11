package com.forum.clothing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * @author zb
 */
@Getter
@AllArgsConstructor
public enum QualityTypeEnum {

    SUMMER("0","夏衣"),
    WINTER("1","冬衣"),
    SHOE("2","鞋子"),
    BAG("3","包包"),
    ;

    private final String qualityType;
    private final String qualityTypeStr;

    public static List<Object> getAllType(){
        return Arrays.stream(QualityTypeEnum.values()).map(v -> {
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put(v.getQualityType(), v.getQualityTypeStr());
            return objectObjectHashMap;
        }).collect(Collectors.toList());
    }

    public static String getTypeStrByCode(String qualityType) {
        for (QualityTypeEnum value : QualityTypeEnum.values()) {
            if (Objects.equals(String.valueOf(qualityType), value.getQualityType())){
                return value.getQualityTypeStr();
            }
        }
        return null;
    }


}
