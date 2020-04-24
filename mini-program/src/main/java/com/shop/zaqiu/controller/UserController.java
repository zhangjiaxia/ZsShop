package com.shop.zaqiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.service.impl.UserServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/zaqiu/user")
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody User user){
        boolean bool = userService.save(user);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = userService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody User user){
        boolean bool = userService.updateById(user);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(HttpServletRequest request){
        User user = userService.getById(httpTool.GetUser(request).getId());
        return ResultVOUtil.success(user);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<User> page = new Page<>(index, pages);
        return ResultVOUtil.success(userService.page(page,null));
    }

    @PostMapping(value = "/findAdmin")
    public ResultVO findAdmin(HttpServletRequest request){
        return ResultVOUtil.success(httpTool.GetUser(request));
    }

}
