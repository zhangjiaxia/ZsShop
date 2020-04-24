package com.shop.zaqiu.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.shop.common.config.KeyConfig;
import com.shop.zaqiu.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.service.impl
 * @date : 2019/10/25 10:17
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public DefaultPutRet FileUpload(MultipartFile file, String bucket) {

        Configuration cfg = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(cfg);

        String key;

        key = System.currentTimeMillis() + file.getOriginalFilename();
        Auth auth = Auth.create(KeyConfig.accessKey, KeyConfig.secretKey);
        String upToken = auth.uploadToken(bucket, key);

        try {
            byte[] bytes;
            bytes = file.getBytes();
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String FileDownUrl(String fileName) throws UnsupportedEncodingException {
        String domainOfBucket = KeyConfig.FileDns;
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        Auth auth = Auth.create(KeyConfig.accessKey, KeyConfig.secretKey);
        long expireInSeconds = KeyConfig.UrlTimeOut;//8小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        // System.out.println(finalUrl+">>>>>>>>>>>");
        return finalUrl;
    }
}
