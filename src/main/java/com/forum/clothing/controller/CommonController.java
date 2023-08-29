package com.forum.clothing.controller;

import com.forum.clothing.service.CommonService;
import com.forum.clothing.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(value = "公共类")
@RestController
public class CommonController {

    @Resource
    private CommonService commonService;

    /**
     * 上传图片
     */
    @ApiOperation("上传图片， 返回图片名称，保存时提交名称即可.")
    @RequestMapping(value = "/uploadImage", method = { RequestMethod.POST})
    public @ResponseBody
    Result<String> uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) {
        return commonService.uploadFile(file);
    }



}
