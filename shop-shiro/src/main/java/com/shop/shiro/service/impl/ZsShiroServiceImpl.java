package com.shop.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manage.service.impl.ZsAdminServiceImpl;
import com.shop.shiro.auth.TokenGenerator;
import com.shop.shiro.entity.ZsShiro;
import com.shop.shiro.mapper.ZsShiroMapper;
import com.shop.shiro.service.IZsShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:18
 * @Version 1.0
 */
@Service
public class ZsShiroServiceImpl extends ServiceImpl<ZsShiroMapper, ZsShiro> implements IZsShiroService {

    @Autowired
    private ZsAdminServiceImpl zsAdminService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //一小时后过期
    private final static Long EXPIRE = 3600 * 1l;

    /**
     * 生成一个token
     *@param  userId
     *@return Result
     */
    public Map<String,Object> createToken(Integer userId) {
        Map<String,Object> result  = new HashMap<>();
        //生成一个token
        String token = TokenGenerator.generateValue();
        result.put("token", token);
        result.put("expire", EXPIRE);
        return result;
    }

    public void logout(Integer userId, String currentToken) {
        //注销当前token
        redisTemplate.opsForValue().set(currentToken, userId.toString(), 1l, TimeUnit.SECONDS);
    }
}
