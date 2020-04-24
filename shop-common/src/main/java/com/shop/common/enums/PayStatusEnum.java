package com.shop.common.enums;

import lombok.Getter;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.enums
 * @date : 2019/10/25 0:42
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
