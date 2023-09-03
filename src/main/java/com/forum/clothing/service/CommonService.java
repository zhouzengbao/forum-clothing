package com.forum.clothing.service;

import com.forum.clothing.enums.AppUserTypeEnum;
import com.forum.clothing.enums.QualityQualityTypeEnum;
import com.forum.clothing.enums.QualityTypeEnum;
import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zb
 */
@Service
public class CommonService {

    @Value("${file.localpath.base}")
    private String baseFilePath;

    public Result<String> uploadFile(MultipartFile file) {

        if (Objects.isNull(file)){
            return Results.failure("文件不能为空");
        }
        // 文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        // // 保存图片的地址
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)){
            return Results.failure("文件不能为空");
        }

        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            File local = new File(baseFilePath + fileName + fileType);
            if (!local.getParentFile().exists()) {
                boolean mkdirs = local.getParentFile().mkdirs();
                if (!mkdirs){
                    throw new RuntimeException("上传失败!");
                }
            }
            file.transferTo(local);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Results.success(fileName + fileType);
    }


    public Result<?> config() {
        Map<String, Object> config = new HashMap<>(3);
        config.put("userType", AppUserTypeEnum.getAllType());
        config.put("qualityType", QualityTypeEnum.getAllType());
        config.put("qualityQualityType", QualityQualityTypeEnum.getAllType());
        return Results.success(config);
    }
}
