package com.shop.zaqiu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.config.KeyConfig;
import com.shop.common.enums.PayStatusEnum;
import com.shop.common.websocket.SocketVo;
import com.shop.common.websocket.WebSocketServer;
import com.shop.zaqiu.dto.MainOrderDto;
import com.shop.zaqiu.dto.SubOrderDto;
import com.shop.zaqiu.entity.*;
import com.shop.zaqiu.mapper.MainOrderMapper;
import com.shop.zaqiu.service.IMainOrderService;
import com.shop.zaqiu.vo.UserCartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@Service
@Slf4j
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements IMainOrderService {

    @Autowired
    private SubOrderServiceImpl subOrderService;

    @Autowired
    private MainOrderServiceImpl mainOrderService;

    @Autowired
    private GameInfoServiceImpl gameInfoService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private MainOrderMapper mainOrderMapper;

    //根据状态查询订单列表
    // List<MainOrder>

    public MainOrder getByOrderId(String orderId) {
        QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
        mainOrderQueryWrapper.eq("orderId", orderId);
        return this.getOne(mainOrderQueryWrapper);
    }

    @Transactional
    //加入购物车，立即购买同一游戏进入订单页面的操作，删除购物车
    public Boolean delCart(User user, UserCartVo userCartVos) {
        //根据用户和游戏ID查询对应的购物车记录
        Cart cart = cartService.getCartByUserIdAndGameId(user.getId(), userCartVos.getGameInfo().getId());
        if(cart == null){
            return true;
        }
        //根据购买游戏的设备列表和购物车记录对比
        List<String> lsOneDevice = JSON.parseArray(cart.getDeviceId(), String.class);
        List<Device> cartDevices = deviceService.findByIds(lsOneDevice);
        userCartVos.getDevices().forEach(device -> {
            cartDevices.remove(device);
        });
        //如果剔除后的设备列表为空，即设备列表完全相等，直接删除购物车记录
        //否则更新购物车游戏的设备列表信息（剔除已经购买了的设备列表）
        if(cartDevices.size() > 0) {
            List<String> updateDevices = new ArrayList<>();
            cartDevices.forEach(device -> {
                updateDevices.add(device.getId().toString());
            });
            cart.setDeviceId(JSON.toJSONString(updateDevices));
            cartService.saveOrUpdate(cart);
        } else {
            cartService.removeById(cart.getId());
        }
        return true;
    }

    @Transactional
    //支付成功
    public Boolean OrderResult(String orderId) {
        //一个主订单下是一个list的子订单，
        MainOrder mainOrder = this.getByOrderId(orderId);
        log.info("【mainOrder：】" + mainOrder);
        mainOrder.setPayState(PayStatusEnum.SUCCESS.getCode());
        mainOrderService.saveOrUpdate(mainOrder);
        List<SubOrder> subOrders = subOrderService.getListSubOrder(orderId);
        HashSet<String> DeviceSet;
        //设备列表
        List<String> devices = new ArrayList<>();
        for(SubOrder order : subOrders) {
            List<String> lsOneDevice = JSON.parseArray(order.getDevicedId(), String.class);
            for(String device : lsOneDevice) {
                devices.add(device);
                log.info("【通知的设备序列号:】" + device);
            }
        }
//        subOrders.forEach(order -> {
//            List<String> lsOneDevice = JSON.parseArray(order.getDevicedId(), String.class);
//            lsOneDevice.forEach(device -> {
//                devices.add(device);
//                log.info("【通知的设备序列号:】" + device);
//            });
//        });
        //根据设备序列号以及订单id 查找设备该订单下购买的游戏
        DeviceSet = new HashSet<String>(devices);

        log.info(DeviceSet.toString());

        for(String device : DeviceSet) {
            log.info(" DeviceSet.forEach");
            log.info(device);
            List<SubOrder> subOrder = subOrderService.getByOrderIdAndDeviceId(orderId, device);
            //通知device下载
            for(SubOrder subOrder1 : subOrder) {
                GameInfo gameInfo = gameInfoService.getById(subOrder1.getGameId());
                //设置游戏销量
                Integer current = gameInfo.getSale() + 1;
                gameInfo.setSale(current);
                gameInfoService.updateById(gameInfo);

                //删除购物车
                //cartService.DelCart(gameInfo.getId(), device);

                //通知下载
                for(SocketVo socketVo : WebSocketServer.socketVos) {
                    //获取设备的唯一标识
                    String sid1 = "";
                    String sid2 = socketVo.getSid();
                    Device device1 = deviceService.getById(device);
                    sid1 = device1.getSerialNumber();
                    log.info("【通知端口】" + sid1 + "," + sid2);
                    log.info("socketVo.getSid()" + socketVo.getSid());
                    if (sid1.equals(sid2)) {
                        try {
                            log.info("【通知的端口:】" + socketVo.getSession().getId());
                            WebSocketServer.SendMessage(ResultVOUtil.SuccessWebSocket(gameInfo, KeyConfig.DOWN), sid2);
                        } catch (IOException e) {
                            e.printStackTrace();
                            //return;
                        }
                    }
                }
            }
        }

//        DeviceSet.forEach(device -> {
//            log.info(" DeviceSet.forEach");
//            log.info(device);
//            List<SubOrder> subOrder = subOrderService.getByOrderIdAndDeviceId(orderId, device);
//            //通知device下载
//            subOrder.forEach(subOrder1 -> {
//                GameInfo gameInfo = gameInfoService.getById(subOrder1.getGameId());
//                //设置游戏销量
//                Integer current = gameInfo.getSale() +1;
//                gameInfo.setSale(current);
//                gameInfoService.updateById(gameInfo);
//
//                //删除购物车
//                //cartService.DelCart(gameInfo.getId(), device);
//
//                //通知下载
//                WebSocketServer.socketVos.forEach(socketVo -> {
//                    //获取设备的唯一标识
//                    String sid1 = "";
//                    //String sid2 = socketVo.getSid();
//                    String sid2 = socketVo.getSession().getId();
//                    Device device1 = deviceService.getById(device);
//                    sid1 = device1.getSerialNumber();
//                    log.info("【通知端口】" + sid1 + "," + sid2);
//                    log.info("socketVo.getSid()" + socketVo.getSid());
//                    if (sid1.equals(sid2)) {
//                        try {
//                            log.info("【通知的端口:】" + socketVo.getSession().getId());
//                            WebSocketServer.SendMessage(ResultVOUtil.SuccessWebSocket(gameInfo, KeyConfig.DOWN), sid2);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return;
//                        }
//                    }
//                });
//            });
//        });

        //修改订单状态
        mainOrder.setPayState(PayStatusEnum.SUCCESS.getCode());
        //该订单下的设备列表
//        if (mainOrder.getTotalSum().toString().equals("0")){
//
//
//        }
        return null;
    }

    //在主订单中找已支付或未支付
    public List<MainOrder> findPayOrder(Integer userId) {
        QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
        mainOrderQueryWrapper.eq("payState", 1).eq("userId", userId).orderByDesc("payState");
        return this.list(mainOrderQueryWrapper);
    }

    //根据orderId删除
    public Boolean removeByOrderId(Integer orderId){
        QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
        mainOrderQueryWrapper.eq("orderId",orderId);
        return this.remove(mainOrderQueryWrapper);
    }


    //获取主订单下所有子订单，游戏信息，设备信息
    public List<MainOrderDto> MainOrderDtos(Integer payState, Integer userId){
        List<MainOrder> mainOrders = new ArrayList<>();
        List<MainOrderDto> mainOrderDtos = new ArrayList<>();
        if (payState == -1)
        {
            QueryWrapper<MainOrder> mainOrderQueryWrapper = new QueryWrapper<>();
            mainOrderQueryWrapper.eq("userId", userId).orderByAsc("payState");
            mainOrders = mainOrderService.list(mainOrderQueryWrapper);
        }
        else
        {
            QueryWrapper<MainOrder> mainOrderQueryWrapper2 = new QueryWrapper<>();
            mainOrderQueryWrapper2.eq("userId", userId).eq("payState",payState).orderByDesc("payState");
            mainOrders = mainOrderService.list(mainOrderQueryWrapper2);
        }

        if (mainOrders.size()==0){
            return null;
        }

        mainOrders.forEach(mainOrder -> {

            MainOrderDto mainOrderDto = new MainOrderDto();
            List<SubOrderDto> subOrderDtos = new ArrayList<>();


            mainOrderDto.setMainOrder(mainOrder);

            List<SubOrder> subOrders = subOrderService.getListSubOrder(mainOrder.getOrderId());

            if (subOrders.size()>0){
                subOrders.forEach(subOrder -> {
                    SubOrderDto subOrderDto = new SubOrderDto();
                    subOrderDto.setSubOrder(subOrder);

                    Integer gameId = subOrder.getGameId();
                    GameInfo gameInfo = gameInfoService.getById(gameId);
                    subOrderDto.setGameInfo(gameInfo);

                    List<Integer> deviceIds = JSON.parseArray(subOrder.getDevicedId(),Integer.class);
                    List<Device> devices = new ArrayList<>();
                    deviceIds.forEach(deviceId->{
                        Device device = deviceService.getById(deviceId);
                        devices.add(device);
                    });
                    subOrderDto.setDevices(devices);

                    subOrderDtos.add(subOrderDto);
                });
            }

            mainOrderDto.setSubOrderDto(subOrderDtos);
            mainOrderDtos.add(mainOrderDto);
        });

        return mainOrderDtos;
    }

    //综合查询
    public List<MainOrder> getOrderListByQuery(String orderId, String gameId, String gameName, String nick, Integer payState){
        List<String> orderList = mainOrderMapper.getOrderId(gameId == null ? "" : gameId, gameName == null ? "" : gameName);
        List<String> userList = mainOrderMapper.getUserId(nick == null ? "" : nick);
        if(orderList.size() == 0) {
            orderList.add("");
        }
        if(userList.size() == 0) {
            userList.add("");
        }
        LambdaQueryWrapper<MainOrder> lambdaQuery = Wrappers.<MainOrder>lambdaQuery();
        lambdaQuery.like(MainOrder::getOrderId, orderId)
                .in(MainOrder::getOrderId, orderList)
                .in(MainOrder::getUserId, userList);
        if (payState != null) {
            lambdaQuery.eq(MainOrder::getPayState, payState);
        }
        return mainOrderMapper.getOrderListByMultipleQuery(lambdaQuery);
    }

}
