package com.shop.zaqiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.zaqiu.entity.UserDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dao
 * @date : 2019/12/3 10:22
 */
@Mapper
public interface UserDeviceDao extends BaseMapper<UserDevice> {
    @Select("SELECT DISTINCT s.devicedId FROM subOrder s INNER JOIN mainOrder m \n" +
            "on s.orderId = m.orderId\n" +
            "WHERE m.userId = #{userId} and s.gameId = #{gameId}")
    List<String> notBuyDevice(int userId, int gameId);
}
