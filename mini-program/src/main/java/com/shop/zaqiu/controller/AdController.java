package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import com.shop.zaqiu.entity.Ad;
import com.shop.zaqiu.service.impl.AdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/zaqiu/ad")
@Slf4j
public class AdController extends BaseController {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private AdServiceImpl adService;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody Ad ad){
        boolean bool = adService.save(ad);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = adService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody Ad ad){
        boolean bool = adService.updateById(ad);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        Ad ad = adService.getById(id);
        return ResultVOUtil.success(ad);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<Ad> page = new Page<>(index, pages);
        return ResultVOUtil.success(adService.page(page,null));
    }

    @GetMapping(value = "/findByAreaAndType")
    public ResultVO findByAreaAndType(@RequestParam String area,@RequestParam String adType){
        return ResultVOUtil.success(adService.findByAreaAdnType(area,adType));
    }

    @GetMapping("/getOneDevice")
    public ResultVO getOneDevice(@RequestParam String uuid){
        Device device = deviceService.findBySerialNumber(uuid);
        return ResultVOUtil.success(device);
    }



}
