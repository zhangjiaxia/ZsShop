package com.shop.zaqiu.dto;

import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.entity.SubOrder;
import lombok.Data;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dto
 * @date : 2019/11/25 10:50
 */
@Data
public class SubOrderDto {
    private SubOrder subOrder;
    private GameInfo gameInfo;
    private List<Device> devices;

    public SubOrderDto() {

    }

    public SubOrderDto(SubOrder subOrder, GameInfo gameInfo, List<Device> devices) {
        this.subOrder = subOrder;
        this.gameInfo = gameInfo;
        this.devices = devices;
    }
}
