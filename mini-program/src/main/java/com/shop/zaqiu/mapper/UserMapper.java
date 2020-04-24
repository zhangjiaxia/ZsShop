package com.shop.zaqiu.mapper;

import com.shop.zaqiu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //@Select("SELECT * FROM blog WHERE id = #{id}")
//    @Select("SELECT DISTINCT s.devicedId FROM subOrder s INNER JOIN mainOrder m \n" +
//            "on s.orderId = m.orderId\n" +
//            "WHERE m.userId = #{userId} and s.gameId = #{gameId}")
//    List<String> notBuyDevice(int userId, int gameId);
}
