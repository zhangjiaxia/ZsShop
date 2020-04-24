package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.zaqiu.dao.DeviceDao;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.mapper.DeviceMapper;
import com.shop.zaqiu.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2019-10-24
 */
@Service
@Slf4j
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceDao deviceDao;

    //查询设备是否合法
    public Device findBySerialNumber(String SerialNumber){
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SerialNumber",SerialNumber);
        return this.getOne(queryWrapper);
    }

    //根据ids查询设备列表
    public List<Device> findByIds(List<String> strDevices){
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", strDevices);
        return this.list(queryWrapper);
    }

    //获取该设备已购买的游戏列表
    public List<GameInfo> findDeviceGameInfoList(String SerialNumber){
        //查询支付订单下的子订单，已知支付的订单号，设备序列号
        List<GameInfo> gameInfos = new ArrayList<>();
        Device device = findBySerialNumber(SerialNumber);
        log.info("【deviceId】"+ device.getId());
        String id = "%"+device.getId().toString()+"%";
        gameInfos = deviceDao.findBySerialNumberHavePay(id);
        return gameInfos;
    }


}
