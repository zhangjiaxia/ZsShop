package com.shop.zaqiu.controller;

import com.qiniu.storage.model.DefaultPutRet;
import com.shop.common.common.QiniuPicUrlFormat;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.config.KeyConfig;
import com.shop.zaqiu.service.impl.FileUploadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.controller
 * @date : 2019/10/25 10:16
 */
@Slf4j
@RestController
@RequestMapping(value = "/File")
public class File {
    @Autowired
    private FileUploadServiceImpl fileUploadService;

    //上传图片，保存到公共空间，作为图床
    @PostMapping("/PicUpload")
    public ResultVO PicUpload(@RequestParam MultipartFile file) {

        DefaultPutRet defaultPutRet = fileUploadService.FileUpload(file, KeyConfig.PicBucket);
        return ResultVOUtil.success(QiniuPicUrlFormat.PicFormat(KeyConfig.PicDns, defaultPutRet.key));
    }

    //上传文件，保存到私人空间，链接有生命周期
    @PostMapping("/FileUpload")
    public ResultVO FileUpload(@RequestParam MultipartFile file) {

        DefaultPutRet defaultPutRet = fileUploadService.FileUpload(file, KeyConfig.FileBucket);
        return ResultVOUtil.success(defaultPutRet);
    }

    //获取文件的下载地址
    @PostMapping("/FileDownUrl")
    public ResultVO FileDownUrl(@RequestParam String fileName) throws UnsupportedEncodingException {
        String downUrl = fileUploadService.FileDownUrl(fileName);
        return ResultVOUtil.success(downUrl);
    }


}
