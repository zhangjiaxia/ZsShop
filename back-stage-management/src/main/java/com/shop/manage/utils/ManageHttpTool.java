package com.shop.manage.utils;

import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.service.impl.ZsAdminServiceImpl;
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
public class ManageHttpTool {

    @Autowired
    private ZsAdminServiceImpl zsAdminService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ManageHttpTool() {
    }

    public  String GetAdminId(HttpServletRequest request){
        String token =  request.getHeader("Authorization");
        String adminId = redisTemplate.opsForValue().get(token);
        return adminId;
    }

    //后台管理的token关联的是adminId
    public ZsAdmin GetAdmin(HttpServletRequest request){ return zsAdminService.findByAdminId(GetAdminId(request)); }

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
