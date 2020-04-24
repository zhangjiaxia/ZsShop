package com.shop.zaqiu.mapper;

import com.shop.zaqiu.entity.UserDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yingran
 * @since 2019-11-07
 */
@Component(value ="UserDeviceMapper")
public interface UserDeviceMapper extends BaseMapper<UserDevice> {
//
//    //@Select("SELECT * FROM blog WHERE id = #{id}")
//    @Select("SELECT DISTINCT s.devicedId FROM subOrder s INNER JOIN mainOrder m \n" +
//            "on s.orderId = m.orderId\n" +
//            "WHERE m.userId = #{userId} and s.gameId = #{gameId}")
//    List<String> notBuyDevice(int userId, int gameId);
}
