package com.shop.zaqiu.controller;

import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.service.impl.WxAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.controller
 * @date : 2019/11/5 10:46
 */
@RestController
@RequestMapping("/zaqiu/test")
public class TestController {
    @Autowired
    private WxAppServiceImpl wxAppService;

    @GetMapping("/wxapp")
    public ResultVO wxapp(){
        return ResultVOUtil.success(wxAppService.getById(1));
    }


}
