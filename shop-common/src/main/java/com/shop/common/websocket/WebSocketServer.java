package com.shop.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.websocket
 * @date : 2019/10/25 9:51
 */
@ServerEndpoint(value = "/ws/{sid}")
@Component
@Slf4j
public class WebSocketServer {
    @PostConstruct
    public void init() {
        System.out.println("websocket 加载...................");
    }
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);

    //public static ConcurrentHashMap<String,Session> BindMap = new ConcurrentHashMap<>();
    public static CopyOnWriteArraySet<SocketVo> socketVos = new CopyOnWriteArraySet<>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {

        socketVos.add(new SocketVo(sid,session));
        log.info("【设备链接】"+sid);
      //  SendMessage(session, ResultVOUtil.SuccessWebSocket("");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid) {

        //BindMap.remove(sid);
        socketVos.removeIf(socketVo -> socketVo.getSid().equals(sid));
        log.info("【设备断开】"+sid);

      }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

        SendMessage(session, "收到消息，消息内容："+message);
        log.info(message);
    }

    /**
     * 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error, @PathParam("sid") String sid) {

        socketVos.removeIf(socketVo -> socketVo.getSid().equals(sid));
        //error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {


    }

    /**
     * 指定Session发送消息
     * @param sid
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message,String sid) throws IOException {
        socketVos.forEach(socketVo -> {
            if (socketVo.getSid().equals(sid) && socketVo.getSession().isOpen()){
                SendMessage(socketVo.getSession(),message);
            }
        });

    }



}
