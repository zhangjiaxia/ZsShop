package com.shop.zaqiu.dto;

import com.shop.zaqiu.entity.MainOrder;
import com.shop.zaqiu.entity.User;
import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dto
 * @date : 2019/11/27 13:48
 */
@Data
public class UserMainOrderDTO {
    private User user;
    private MainOrder mainOrder;

    public UserMainOrderDTO() {

    }

    public UserMainOrderDTO(User user, MainOrder mainOrder) {
        this.user = user;
        this.mainOrder = mainOrder;
    }
}
