package com.shop.common.common;

import com.shop.common.enums.ResultEnum;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)

    //专门处理shiro框架没有权限的异常
    @ResponseBody
    public ResultVO jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResultVOUtil.error(ResultEnum.AUTH_NOT_ENOUGH.getCode(), ResultEnum.AUTH_NOT_ENOUGH.getMessage());
    }

}