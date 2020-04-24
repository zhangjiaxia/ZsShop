package com.shop.zaqiu.controller;

import com.alibaba.fastjson.JSON;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.enums.ResultEnum;
import com.shop.zaqiu.dao.UserDeviceDao;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.entity.UserDevice;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import com.shop.zaqiu.service.impl.UserDeviceServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/zaqiu/user-device")
@Slf4j
public class UserDeviceController extends BaseController {

    @Autowired
    private UserDeviceServiceImpl userDeviceService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private HttpTool httpTool;

    @Autowired
    private UserDeviceDao deviceMapper;

    //用户绑定设备
    @GetMapping(value = "/bindDevice")
    public ResultVO bindDevice(@RequestParam String serialNumber, HttpServletRequest request){

        Device device = deviceService.findBySerialNumber(serialNumber);
        if (device == null){
            return ResultVOUtil.error(ResultEnum.BINDDEVICE_ERROR.getCode(),ResultEnum.BINDDEVICE_ERROR.getMessage());
        }

        if (userDeviceService.findByUserIdAndDeviceId(httpTool.GetUser(request).getId(),device.getId()) != null)
        {
            return ResultVOUtil.success(ResultEnum.BINDDEVICEREPEAT_ERROR.getMessage());
        }

        UserDevice userDevice = new UserDevice();
        userDevice.setUserId(httpTool.GetUser(request).getId());
        userDevice.setDeviceId(device.getId());

        return ResultVOUtil.success(userDeviceService.saveOrUpdate(userDevice));
    }

    //获取用户设备
    @GetMapping(value = "/listByUserId")
    public ResultVO listByUserId(HttpServletRequest request){

        List<UserDevice> userDeviceList = userDeviceService.getUserDevices(httpTool.GetUser(request).getId());

        if (userDeviceList.size() == 0){
            return ResultVOUtil.error(ResultEnum.DEVICE_NO.getCode(),ResultEnum.DEVICE_NO.getMessage());
        }

        List<Device> devices = new ArrayList<>();

        userDeviceList.forEach(userDevice -> {
            devices.add(deviceService.getById(userDevice.getDeviceId()));
        });

        return ResultVOUtil.success(devices);
    }

    //查询该游戏曾经安装过的设备
    @GetMapping(value = "/listCanByDevice")
    public ResultVO listCanByDevice(@RequestParam Integer gameId,HttpServletRequest request){
          User user = httpTool.GetUser(request);
        //一个string的格式是一个id列表
        List<String> ids = deviceMapper.notBuyDevice(user.getId(),gameId);

        //用户拥有的设备
        List<UserDevice> devices = userDeviceService.getUserDevices(user.getId());

        Set<String> deviceId = new HashSet<>();
        //用户的设备id
        Set<String> res = new HashSet<>();
        Set<String> result  = new HashSet<>();

        ids.forEach(id->{
            List<String> ids2 = JSON.parseArray(id,String.class);
            ids2.forEach(id3->{
                deviceId.add(id3);
            });

        });


        devices.forEach(devicesUser->{
            res.add(devicesUser.getDeviceId().toString());
        });


        result.addAll(res);
        result.removeAll(deviceId);

        List<Device> deviceList = new ArrayList<>();
        result.forEach(resId->{
            deviceList.add(deviceService.getById(resId));
        });

        return ResultVOUtil.success(deviceList);

    }




}
