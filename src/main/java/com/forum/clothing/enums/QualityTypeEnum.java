package com.forum.clothing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum QualityTypeEnum {

    SUMMER("1","夏装"),
    WINTER("2","冬装"),
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
}
