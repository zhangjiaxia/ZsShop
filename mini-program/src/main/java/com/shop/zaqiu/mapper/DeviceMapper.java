package com.shop.zaqiu.mapper;

import com.shop.zaqiu.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.zaqiu.entity.GameInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */

@Component(value = "DeviceMapper")
public interface DeviceMapper extends BaseMapper<Device> {

//    @Select("SELECT * FROM gameInfo WHERE id in\n" +
//            "(SELECT DISTINCT s.gameId FROM mainOrder m INNER JOIN subOrder s on s.orderId = m.orderId\n" +
//            "WHERE m.payState = 1 AND s.devicedId like #{key}\n" +
//            ")")
//    List<GameInfo> findBySerialNumberHavePay(String key);

}
