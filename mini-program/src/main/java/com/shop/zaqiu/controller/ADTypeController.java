package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.ADType;
import com.shop.zaqiu.service.impl.ADTypeServiceImpl;
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
@RequestMapping("/zaqiu/a-dtype")
public class ADTypeController extends BaseController {
    @Autowired
    private ADTypeServiceImpl adTypeService;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody ADType adType){
        boolean bool = adTypeService.save(adType);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = adTypeService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody ADType adType){
        boolean bool = adTypeService.updateById(adType);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        ADType adType = adTypeService.getById(id);
        return ResultVOUtil.success(adType);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<ADType> page = new Page<>(index, pages);
        return ResultVOUtil.success(adTypeService.page(page,null));
    }
}
