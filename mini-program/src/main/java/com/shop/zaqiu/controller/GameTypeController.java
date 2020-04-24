package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.GameType;
import com.shop.zaqiu.service.impl.GameTypeServiceImpl;
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
@RequestMapping("/zaqiu/game-type")
public class GameTypeController extends BaseController {

    @Autowired
    private GameTypeServiceImpl gameTypeService;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody GameType gameType){
        boolean bool = gameTypeService.save(gameType);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = gameTypeService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody GameType gameType){
        boolean bool = gameTypeService.updateById(gameType);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        GameType gameType = gameTypeService.getById(id);
        return ResultVOUtil.success(gameType);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<GameType> page = new Page<>(index, pages);
        return ResultVOUtil.success(gameTypeService.page(page,null));
    }

}
