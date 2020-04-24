package com.shop.shiro.auth;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.shop.common.common.HttpContextUtils;
import com.shop.common.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * auth过滤器
 *
 * @Author CrazyJay
 * @Date 2019/3/31 10:38
 * @Version 1.0
 */
public class AuthFilter extends AuthenticatingFilter {
    /**
     * 生成自定义token
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new AuthToken(token);
    }

    /**
     * 步骤1.所有请求全部拒绝访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    /**
     * 步骤2，拒绝访问的请求，会调用onAccessDenied方法，onAccessDenied方法先获取 token，再调用executeLogin方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            //处理登录失败的异常
            Map<String, Object> result = new HashMap<>();
            result.put("code", ResultEnum.TOKEN_EMPTY.getCode());
            result.put("msg", ResultEnum.TOKEN_EMPTY.getMessage());
            String json = new Gson().toJson(result);
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request, response);
    }
    /**
     * 登陆失败时候调用
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Map<String, Object> result = new HashMap<>();
            result.put("code", ResultEnum.TOKEN_EXPIRE.getCode());
            result.put("msg", ResultEnum.TOKEN_EXPIRE.getMessage());
            String json = JSON.toJSONString(result);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
        }
        return false;
    }
    /**
     * 获取请求的token
     */
    public String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("Authorization");
        //如果header中不存在token，则从参数中获取token
//        if (StringUtils.isBlank(token)) {
//            token = httpRequest.getParameter("token");
//        }
        return token;
    }
}
