package com.shop.zaqiu.vo;

import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.entity.UserStar;
import lombok.Data;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/10/31 10:40
 */
@Data
public class StarGameVo {
    private UserStar userStar; //收藏表
    private GameInfo gameInfo;
}
