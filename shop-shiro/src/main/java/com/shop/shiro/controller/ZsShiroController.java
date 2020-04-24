package com.shop.shiro.controller;


import com.alibaba.fastjson.JSONObject;
import com.shop.common.common.SellException;
import com.shop.common.enums.ResultEnum;
import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.entity.ZsPermission;
import com.shop.manage.service.impl.ZsAdminServiceImpl;
import com.shop.manage.service.impl.ZsPermissionServiceImpl;
import com.shop.shiro.service.impl.ZsShiroServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:04
 * @Version 1.0
 */
@RestController
public class ZsShiroController {

    @Autowired
    private ZsShiroServiceImpl shiroService;

    @Autowired
    private ZsAdminServiceImpl zsAdminService;

    @Autowired
    ZsPermissionServiceImpl zsPermissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @GetMapping("/sys/login")
    public Map<String, Object> login(String username, String password)  {
        Map<String, Object> result = new HashMap<>();
        //用户信息
        ZsAdmin zsAdmin = zsAdminService.getAdminByName(username);
        //账号不存在、密码错误
        if (zsAdmin==null||!zsAdmin.getPassword().equals(password)) {
            result.put("status", "400");
            result.put("msg", "账号或密码有误");
        } else {
            //设置登录时间
            zsAdmin.setLoginTime(new Date());
            zsAdminService.saveOrUpdate(zsAdmin);
            //生成token，并保存到数据库
            result = shiroService.createToken(zsAdmin.getId());
            //设置token关联管理员ID
            redisTemplate.opsForValue().set(result.get("token").toString(), zsAdmin.getId().toString(), (long)result.get("expire"), TimeUnit.SECONDS);
            List<ZsPermission> menus = zsPermissionService.findMenusById(zsAdmin.getId());
            JSONObject userBaseInfo = new JSONObject();
            userBaseInfo.put("baseInfo", zsAdmin);
            userBaseInfo.put("menus", menus);
            result.put("code", 0);
            result.put("msg", "登陆成功");
            result.put("data", userBaseInfo);
        }
        return result;
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        String token =  request.getHeader("Authorization");
        if(token == null) {
            throw new SellException(ResultEnum.TOKEN_EMPTY.getCode() ,ResultEnum.TOKEN_EMPTY.getMessage());
        }
        Map<String, Object> result = new HashMap<>();
        ZsAdmin user = (ZsAdmin) SecurityUtils.getSubject().getPrincipal();
        shiroService.logout(user.getId(), token);
        result.put("code", 0);
        result.put("msg", "退出登录成功");
        return result;
    }

}


