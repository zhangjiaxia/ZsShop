package com.shop.zaqiu.dto;

import com.shop.zaqiu.entity.MainOrder;
import lombok.Data;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dto
 * @date : 2019/11/27 13:48
 */
@Data
public class MainOrderDto {
    private MainOrder mainOrder;
    private List<SubOrderDto> subOrderDto;
}
