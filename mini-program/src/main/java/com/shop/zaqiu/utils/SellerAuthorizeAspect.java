package com.shop.zaqiu.utils;

import com.shop.common.common.SellException;
import com.shop.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.common
 * @date : 2019/10/30 11:51
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //这个类用来处理小程序端需要token的api的拦截问题
    @Pointcut("execution(public * com.shop.zaqiu.controller.AddressController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.CartController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.DeviceController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.MainOrderController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.PayController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.SubOrderController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.UserController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.UserDeviceController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.UserStarController*.*(..))"+
            "|| execution(public * com.shop.zaqiu.controller.WebSocketController*.*(..))"
    )
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token =  request.getHeader("Authorization");
        if(token == null) {
            throw new SellException(ResultEnum.TOKEN_EMPTY.getCode() ,ResultEnum.TOKEN_EMPTY.getMessage());
        }
        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(tokenValue)) {
            throw new SellException(ResultEnum.TOKEN_EMPTY.getCode() ,ResultEnum.TOKEN_EMPTY.getMessage());
        }
    }
}
