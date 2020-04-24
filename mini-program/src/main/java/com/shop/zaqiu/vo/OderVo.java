package com.shop.zaqiu.vo;

import com.shop.zaqiu.entity.MainOrder;
import com.shop.zaqiu.entity.SubOrder;
import lombok.Data;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/11/14 9:26
 */
@Data
public class OderVo {

    private List<MainOrder> mainOrders;
    private List<SubOrder> subOrders;

}
