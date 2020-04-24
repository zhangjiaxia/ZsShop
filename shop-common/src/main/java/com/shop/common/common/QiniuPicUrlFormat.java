package com.shop.common.common;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/25 10:19
 */
public class QiniuPicUrlFormat {
    public static String PicFormat(String Dns, String PicName) {
        return String.format("%s/%s", Dns, PicName);
    }
}
