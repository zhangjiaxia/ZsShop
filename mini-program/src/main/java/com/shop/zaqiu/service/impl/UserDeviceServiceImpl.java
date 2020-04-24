package com.shop.zaqiu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.zaqiu.entity.*;
import com.shop.zaqiu.mapper.UserDeviceMapper;
import com.shop.zaqiu.service.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-11-07
 */
@Service
public class UserDeviceServiceImpl extends ServiceImpl<UserDeviceMapper, UserDevice> implements IUserDeviceService {

    @Autowired
    private MainOrderServiceImpl mainOrderService;
    @Autowired
    private SubOrderServiceImpl subOrderService;
    @Autowired
    private DeviceServiceImpl deviceService;

    //获取用户设备列表
    public List<UserDevice> getUserDevices(Integer userId){
        QueryWrapper<UserDevice> userDeviceQueryWrapper = new QueryWrapper<>();
        userDeviceQueryWrapper.eq("userId",userId);
        return list(userDeviceQueryWrapper);
    }

    public List<UserDevice> getByDeviceId(Integer deviceId){
        QueryWrapper<UserDevice> deviceQueryWrapper = new QueryWrapper<>();
        deviceQueryWrapper.eq("deviceId",deviceId);
        return this.list(deviceQueryWrapper);
    }


    //获取可以购买某某游戏的设备
    public List<UserDevice> getCanByDevice(Integer userId, Integer gameId){

        List<UserDevice> userDevices = getUserDevices(userId);

        List<MainOrder> mainOrders = mainOrderService.findPayOrder(userId);
        mainOrders.forEach(mainOrder -> {
            List<SubOrder> subOrders= subOrderService.getPayDevice(mainOrder.getOrderId(),gameId);
            subOrders.forEach(subOrder -> {
               // Device device =deviceService.getById(subOrder.getDevicedId());
                userDevices.forEach(userDevice -> {
                      //List<String> strings =JSON.parseObject(subOrder.getDevicedId(),String);

                    List<String> deviceIds = new ArrayList<>();
                    deviceIds = JSON.parseArray(subOrder.getDevicedId(),String.class);

                    deviceIds.forEach(id->{
                        if (userDevice.getDeviceId().toString() == id){
                            userDevices.remove(userDevice);
                        }
                    });


                });

            });
        });

        return userDevices;

    }


    //检查是否已绑定
    public UserDevice findByUserIdAndDeviceId(Integer userId,Integer deviceId){
        QueryWrapper<UserDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId).eq("deviceId",deviceId);
        return this.getOne(queryWrapper);

    }




}
