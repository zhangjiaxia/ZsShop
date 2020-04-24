package com.shop.zaqiu.controller;

import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.*;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.controller
 * @date : 2019/11/13 16:17
 */

@RestController
@RequestMapping("/PublicController")
@Slf4j
public class PublicController {

    @Autowired
    private DeviceServiceImpl deviceService;

//    @Autowired
//    private ZsAdminServiceImpl zsAdminService;
//
//    @Autowired
//    ZsPermissionServiceImpl zsPermissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //查询该设备拥有的游戏
    @PostMapping("/findDeviceGames")
    public ResultVO findDeviceGames(@RequestParam String SerialNumber){
        return ResultVOUtil.success(deviceService.findDeviceGameInfoList(SerialNumber));
    }

//    @PostMapping(value = "/login")
//    public ResultVO login(@RequestBody JSONObject jsonObject){
//        String username = jsonObject.getString("username");
//        String password = jsonObject.getString("password");
//        ZsAdmin zsAdmin = zsAdminService.findAdmin(username, password);
//        if(zsAdmin == null) {
//            return ResultVOUtil.success("账号或密码错误");
//        }
//        //设置登录时间
//        zsAdmin.setLoginTime(new Date());
//        zsAdminService.saveOrUpdate(zsAdmin);
//        String token = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(token, zsAdmin.getId().toString());
//        //查询该管理员的权限菜单
//        List<ZsPermission> menus = zsPermissionService.findMenusById(zsAdmin.getId());
//        JSONObject userBaseInfo = new JSONObject();
//        userBaseInfo.put("token", token);
//        userBaseInfo.put("baseInfo", zsAdmin);
//        userBaseInfo.put("menus", menus);
//        return ResultVOUtil.success(userBaseInfo);
//    }

    //判断是否合法性
    @PostMapping("/VerifyDevice")
    public ResultVO VerifyDevice(@RequestParam String SerialNumber){
        Device device = deviceService.findBySerialNumber(SerialNumber);
        return ResultVOUtil.success(device);
    }

}
