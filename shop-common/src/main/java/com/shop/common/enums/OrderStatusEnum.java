package com.shop.common.enums;

import lombok.Getter;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.enums
 * @date : 2019/10/25 0:39
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
            ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
