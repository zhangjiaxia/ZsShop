package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.dao.AddressDao;
import com.shop.zaqiu.entity.Address;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.service.impl.AddressServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/zaqiu/address")
@Slf4j
public class AddressController extends BaseController {
    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody Address address, HttpServletRequest request){
        address.setUserId(httpTool.GetUser(request).getId());
        boolean bool = addressService.SaveDefault(address);

        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = addressService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody Address address){

        if (address.getIsDefault() == 1){
            addressDao.UpdateToDefault(address.getUserId());
        }

        boolean bool = addressService.updateById(address);
        return ResultVOUtil.success(bool);
    }

    @GetMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        Address address = addressService.getById(id);
        return ResultVOUtil.success(address);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages,HttpServletRequest request){
        IPage<Address> page = new Page<>(index, pages);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",httpTool.GetUser(request).getId());
        return ResultVOUtil.success(addressService.page(page,queryWrapper));
    }

    @GetMapping(value = "/getDefault")
    public ResultVO getDefault(HttpServletRequest request){
        User user = httpTool.GetUser(request);
        Address address = addressService.getDefault(user.getId());
        return ResultVOUtil.success(address);
    }




}
