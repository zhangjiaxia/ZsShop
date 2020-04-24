package com.shop.zaqiu.vo;

import com.shop.zaqiu.entity.Cart;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import lombok.Data;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/10/31 10:40
 */
@Data
public class UserCartVo {
    private Cart cart;
    private GameInfo gameInfo;
    private List<Device> devices;
}
