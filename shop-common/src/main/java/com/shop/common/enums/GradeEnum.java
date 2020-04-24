package com.shop.common.enums;

import lombok.Getter;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.enums
 * @date : 2019/11/7 14:01
 */
@Getter
public enum  GradeEnum implements CodeEnum {
    ADMIN(0, "管理员"),
    USER(1, "普通用户"),
    VIP(2,"会员"),
            ;

    private Integer code;
    private String message;

    GradeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
