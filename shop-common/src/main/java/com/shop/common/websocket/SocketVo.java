package com.shop.common.websocket;

import lombok.Data;

import javax.websocket.Session;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.vo
 * @date : 2019/11/19 17:35
 */
@Data
public class SocketVo {
    private String sid; //对应设备序列号，唯一
    private Session session; //每台硬件设备的唯一标志

    public SocketVo(String sid, Session session) {
        this.sid = sid;
        this.session = session;
    }
}
