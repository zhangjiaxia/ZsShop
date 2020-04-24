package com.shop.zaqiu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.config.KeyConfig;
import com.shop.common.websocket.WebSocketServer;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.service.impl.GameInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.controller
 * @date : 2019/10/25 9:53
 */

@RestController
@RequestMapping("/ws")
@Slf4j
public class WebSocketController {
    @Autowired
    private GameInfoServiceImpl gameInfoService;
    /**
     * 群发消息内容
     * @param message
     * @return
     */
    @RequestMapping(value="/sendAll", method= RequestMethod.GET)
    public String sendAllMessage(@RequestParam(required=true) String message){
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param sid 设备唯一标识吗
     * @return
     */
    @RequestMapping(value="/sendOne", method= RequestMethod.GET)
    public String sendOneMessage(@RequestParam(required=true) String message, @RequestParam(required=true) String sid){
        WebSocketServer.socketVos.forEach(socketVo -> {
            if (socketVo.getSid().equals(sid) && socketVo.getSession().isOpen()){
                WebSocketServer.SendMessage(socketVo.getSession(),message);
            }
        });
        return "ok";
    }

    //websocket测试
    @RequestMapping(value="/sendDown", method= RequestMethod.GET)
    public String SendDown(@RequestParam(required = true) String uuid,@RequestParam(required = true) String gameABName){
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameName(gameABName);
        QueryWrapper<GameInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("gameABName",gameABName);
        gameInfo = gameInfoService.oneByTypeName(gameABName);

        try {
            WebSocketServer.SendMessage(ResultVOUtil.SuccessWebSocket(gameInfo, KeyConfig.DOWN),uuid);

        } catch (IOException e) {
            e.printStackTrace();
            return "发送失败！";
        }
        return "已发送！";
    }

}
