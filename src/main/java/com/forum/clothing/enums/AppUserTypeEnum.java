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
public enum AppUserTypeEnum {
    SUPPLIER("0", "原材料供应商"),
    YARD("1", "分拣场"),
    MERCHANT("2", "贸易商"),
    ;
    private final String userType;
    private final String userTypeStr;

    public static List<Object> getAllType(){
        return Arrays.stream(AppUserTypeEnum.values()).map( v -> {
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put(v.getUserType(), v.getUserTypeStr());
            return objectObjectHashMap;
        }).collect(Collectors.toList());
    }

    public static String getUserTypeStr(Integer userType) {
        for (AppUserTypeEnum value : AppUserTypeEnum.values()) {
            if (Objects.equals(String.valueOf(userType), value.getUserType())){
                return value.getUserTypeStr();
            }
        }
        return null;
    }
}
