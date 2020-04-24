package com.shop.zaqiu.vo;

import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/11/6 14:33
 */
@Data
public class Detail {
    private String code;
    private String encryptedData;
    private String iv;
    private String errMsg;
    private String rawData;
    private String signature;
    private UserInfoVo userInfo;
}
