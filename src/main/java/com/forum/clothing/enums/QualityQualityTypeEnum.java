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
public enum QualityQualityTypeEnum {

    SUPPLY("0","供"),
    NEED("1","需"),
    ;

    private final String qualityType;
    private final String qualityTypeStr;

    public static List<Object> getAllType(){
        return Arrays.stream(QualityQualityTypeEnum.values()).map(v -> {
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put(v.getQualityType(), v.getQualityTypeStr());
            return objectObjectHashMap;
        }).collect(Collectors.toList());
    }


    public static String getQualityTypeStrByCode(Byte qualityType) {
        for (QualityQualityTypeEnum value : QualityQualityTypeEnum.values()) {
            if (Objects.equals(String.valueOf(qualityType), value.getQualityType())){
                return value.getQualityTypeStr();
            }
        }
        return null;
    }
}
