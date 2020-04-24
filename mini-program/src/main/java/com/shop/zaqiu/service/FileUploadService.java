package com.shop.zaqiu.service;

import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.service
 * @date : 2019/10/25 10:20
 */
public interface FileUploadService {
    DefaultPutRet FileUpload(MultipartFile file, String bucket);

    String FileDownUrl(String FileName) throws UnsupportedEncodingException;
}
