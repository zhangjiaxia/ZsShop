package com.shop.common.common;

import com.shop.common.enums.ResultEnum;
import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/25 0:45
 */
@Data
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
