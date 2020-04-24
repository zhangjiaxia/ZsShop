package com.shop.common.enums;

import lombok.Getter;

/**
 * @author : jiaxia
 * Project: shop
 * Package: com.yingran.shop.enums
 * @date : 2019/12/13 10:00
 */
@Getter
public enum ProductTypeEnum {
    METOPE(1, "墙面互动"),
    GROUND(2, "地面互动"),
    BODYSENSATION(3, "体感互动"),
    ;

    private Integer code;

    private String message;

    ProductTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getProductType(int index) {
        for (ProductTypeEnum c : ProductTypeEnum.values()) {
            if (c.getCode() == index) {
                return c.message;
            }
        }
        return null;
    }
}
