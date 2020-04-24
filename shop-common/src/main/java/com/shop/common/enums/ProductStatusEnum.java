package com.shop.common.enums;

import lombok.Getter;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.enums
 * @date : 2019/10/25 0:44
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
