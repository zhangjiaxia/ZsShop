package com.shop.zaqiu.vo;

import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/10/30 11:13
 */

@Data
public class UserInfoVo {
    private String country;
    private Integer gender;
    private String province;
    private String city;
    private String avatarUrl;
    private String nickName;
    private String zh_CN;

}
