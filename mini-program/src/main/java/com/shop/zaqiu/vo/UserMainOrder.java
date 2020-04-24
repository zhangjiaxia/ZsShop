package com.shop.zaqiu.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/11/1 10:35
 */
@Data
public class UserMainOrder {
    private String addressId;
    private String message;
    private List<UserCartVo> userCartVos;

}
