package com.shop.zaqiu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.shop.zaqiu.entity.MainOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
//@Component(value ="MainOrderMapper")
public interface MainOrderMapper extends BaseMapper<MainOrder> {

    //根据订单编号，游戏ID，游戏名，用户昵称查询订单列表#{adminId}
//    @Select("SELECT * FROM mainOrder m where \n" +
//            "m.orderId like CONCAT('%','','%')\n" +
//            "and userId IN ( SELECT u.id FROM user u where u.nick LIKE '%%') \n" +
//            "and m.orderId IN ( SELECT s.orderId FROM subOrder s where s.gameId IN ( SELECT g.id FROM gameInfo g where g.gameName LIKE '%%' and g.id like '%%'))")
//    List<MainOrder> getOrderListByMultipleQuery(String orderId, Integer gameId, String gameName, String nick);

    @Select("SELECT u.id FROM user u where u.nick LIKE CONCAT('%',#{nick},'%')")
    List<String> getUserId(String nick);

    @Select("SELECT s.orderId FROM subOrder s where s.gameId IN ( SELECT g.id FROM gameInfo g where g.gameName LIKE CONCAT('%',#{gameName},'%') and g.id like CONCAT('%',#{gameId},'%'))")
    List<String> getOrderId(String gameId, String gameName);

    @Select("SELECT * FROM mainOrder ${ew.customSqlSegment}")
    List<MainOrder> getOrderListByMultipleQuery(@Param(Constants.WRAPPER) LambdaQueryWrapper wrapper);
}
