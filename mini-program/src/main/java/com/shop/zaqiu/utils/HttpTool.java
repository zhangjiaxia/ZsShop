package com.shop.zaqiu.utils;

import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.common
 * @date : 2019/10/30 14:54
 */

@Slf4j
@Component
public class HttpTool {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public HttpTool() {
    }

    public  String GetOpenId(HttpServletRequest request){
        String token =  request.getHeader("Authorization");
        String openid = redisTemplate.opsForValue().get(token);
        return openid;
    }

    //小程序的token关联的是openId
    public User GetUser(HttpServletRequest request){
        return userService.getOneByOpenId(GetOpenId(request));
    }

    //读取本地文档
    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }



}
