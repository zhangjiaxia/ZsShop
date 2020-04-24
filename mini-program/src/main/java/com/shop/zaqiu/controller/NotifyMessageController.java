package com.shop.zaqiu.controller;


import com.alibaba.fastjson.JSONObject;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.NotifyMessage;
import com.shop.zaqiu.service.impl.NotifyMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/zaqiu/notify-message")
public class NotifyMessageController extends BaseController {

    @Autowired
    private NotifyMessageServiceImpl notifyMessageService;

    @GetMapping("/all")
    public ResultVO all(){
        return ResultVOUtil.success(notifyMessageService.list());
    }

    @PostMapping("/addOne")
    public ResultVO addOne(@RequestBody NotifyMessage notifyMessage){
        return ResultVOUtil.success(notifyMessageService.save(notifyMessage));
    }

    @PostMapping("/updateOne")
    public ResultVO updateOne(@RequestBody NotifyMessage notifyMessage){
        return ResultVOUtil.success(notifyMessageService.saveOrUpdate(notifyMessage));
    }

    @PostMapping("/delOne")
    public ResultVO delOne(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInteger("id");
        return ResultVOUtil.success(notifyMessageService.removeById(id));
    }



}
