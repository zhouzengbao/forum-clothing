package com.forum.clothing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AppUserTypeEnum {
    SUPPLIER("1", "原材料供应商"),
    YARD("2", "分拣场"),
    MERCHANT("3", "贸易商"),
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
}
