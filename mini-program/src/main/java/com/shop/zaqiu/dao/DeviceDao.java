package com.shop.zaqiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dao
 * @date : 2019/12/3 10:03
 */
@Mapper
public interface DeviceDao extends BaseMapper<Device> {
    @Select("SELECT * FROM gameInfo WHERE id in\n" +
            "(SELECT DISTINCT s.gameId FROM mainOrder m INNER JOIN subOrder s on s.orderId = m.orderId\n" +
            "WHERE m.payState = 1 AND s.devicedId like #{key}\n" +
            ")")
    List<GameInfo> findBySerialNumberHavePay(String key);
}
