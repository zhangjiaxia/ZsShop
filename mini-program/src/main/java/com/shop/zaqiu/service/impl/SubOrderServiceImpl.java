package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.SubOrder;
import com.shop.zaqiu.mapper.SubOrderMapper;
import com.shop.zaqiu.service.ISubOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@Service
public class SubOrderServiceImpl extends ServiceImpl<SubOrderMapper, SubOrder> implements ISubOrderService {

    @Autowired
    private MainOrderServiceImpl mainOrderService;

    public List<SubOrder> getListSubOrder(String orderId){
        QueryWrapper<SubOrder> subOrderQueryWrapper = new QueryWrapper<>();
        subOrderQueryWrapper.eq("orderId",orderId);
        return this.list(subOrderQueryWrapper);
    }

    //根据设备id和订单id查找拥有的游戏
    public List<SubOrder> getByOrderIdAndDeviceId(String orderId,String deviceId){
        QueryWrapper<SubOrder> subOrderQueryWrapper = new QueryWrapper<>();
        subOrderQueryWrapper.eq("orderId",orderId)
        .like("devicedId",deviceId);
        return this.list(subOrderQueryWrapper);
    }

    //获取已购买某某游戏的设备
    public List<SubOrder> getPayDevice(String orderId,Integer gameId){
        QueryWrapper<SubOrder> subOrderQueryWrapper = new QueryWrapper<>();
        subOrderQueryWrapper.eq("orderId",orderId)
                .like("gameId",gameId);
        return this.list(subOrderQueryWrapper);
    }

    public List<SubOrder> getByDeviceId(Integer deviceId){
        QueryWrapper<SubOrder> subOrderQueryWrapper = new QueryWrapper<>();
        subOrderQueryWrapper.like("devicedId",deviceId);

        return this.list(subOrderQueryWrapper);
    }

    public Boolean removeByOrderId(Integer orderId){

        QueryWrapper<SubOrder> subOrderQueryWrapper = new QueryWrapper<>();
        subOrderQueryWrapper.eq("orderId",orderId);

        Boolean b = this.remove(subOrderQueryWrapper);
        return b;
    }


}
