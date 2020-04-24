package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.enums.ProductTypeEnum;
import com.shop.zaqiu.utils.HttpTool;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.service.impl.GameInfoServiceImpl;
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
@RequestMapping("/zaqiu/game-info")
@Slf4j
public class GameInfoController extends BaseController {
    @Autowired
    private GameInfoServiceImpl gameInfoService;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody GameInfo gameInfo){
        boolean bool = gameInfoService.save(gameInfo);
        return ResultVOUtil.success(bool);
    }

    @GetMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = gameInfoService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody GameInfo gameInfo){
        boolean bool = gameInfoService.updateById(gameInfo);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        GameInfo gameInfo = gameInfoService.getById(id);
        return ResultVOUtil.success(gameInfo);
    }

    //获取产品分类
    @GetMapping(value = "/pageByTypeId")
    public ResultVO pageByTypeId(@RequestParam Integer index,@RequestParam Integer typeId,@RequestParam int pages){
        IPage<GameInfo> page = new Page<>(index, pages);
        QueryWrapper<GameInfo> gameInfoQueryWrapper = new QueryWrapper<>();
        gameInfoQueryWrapper.eq("typeId",typeId);

        return ResultVOUtil.success(gameInfoService.page(page,gameInfoQueryWrapper));
    }

    //主页获取各类型的产品,传0 获取全部
    @GetMapping(value = "/pageByProductType")
    public ResultVO pageByProductType(@RequestParam Integer productTypeId,@RequestParam Integer index,@RequestParam int pages){
        if (productTypeId == 0){
            return ResultVOUtil.success(gameInfoService.list());
        }else
        {
            IPage<GameInfo> page = new Page<>(index, pages);
            QueryWrapper<GameInfo> gameInfoQueryWrapper = new QueryWrapper<>();
            gameInfoQueryWrapper.eq("productType",productTypeId);
            return ResultVOUtil.success(gameInfoService.page(page,gameInfoQueryWrapper));
        }
    }

    //根据商品名和商品状态来查询
    @GetMapping(value = "/pageByWordAndStatus")
    public ResultVO pageByWordAndStatus(@RequestParam(required=false) Integer state,@RequestParam(required=false) String searchKey,
                                        @RequestParam Integer index,@RequestParam int pages){
        IPage<GameInfo> page = new Page<>(index, pages);
        QueryWrapper<GameInfo> gameInfoQueryWrapper = new QueryWrapper<>();
        if(state != null) {
            gameInfoQueryWrapper.eq("state",state);
        }
        if(!searchKey.equals("")) {
            gameInfoQueryWrapper.like("gameName",searchKey);
        }
        return ResultVOUtil.success(gameInfoService.page(page,gameInfoQueryWrapper));
    }

    //模糊搜索
    @GetMapping(value = "/pageBySearchKey")
    public ResultVO pageBySearchKey(@RequestParam Integer index,@RequestParam String SearchKey,@RequestParam int pages){
        IPage<GameInfo> page = new Page<>(index, pages);
        QueryWrapper<GameInfo> gameInfoQueryWrapper = new QueryWrapper<>();
        //产品类型搜索，或者游戏名模糊搜索
        Integer productTypeId = -1;
        if ("互动".indexOf(SearchKey) != -1) {
            SearchKey = "";
        } else {
            if(ProductTypeEnum.getProductType(1).indexOf(SearchKey) != -1) {
                productTypeId = 1;
            } else if(ProductTypeEnum.getProductType(2).indexOf(SearchKey) != -1) {
                productTypeId = 2;
            } else if(ProductTypeEnum.getProductType(3).indexOf(SearchKey) != -1) {
                productTypeId = 3;
            }
        }
        if (productTypeId != -1) {
            gameInfoQueryWrapper.eq("productType",productTypeId);
        } else {
            gameInfoQueryWrapper.like("gameName",SearchKey);
        }
        return ResultVOUtil.success(gameInfoService.page(page,gameInfoQueryWrapper));
    }

    //获取最新的
    @GetMapping(value = "/pageOrderById")
    public ResultVO pageOrderById(@RequestParam Integer index,@RequestParam int pages){
        IPage<GameInfo> page = new Page<>(index, pages);
        QueryWrapper<GameInfo> gameInfoQueryWrapper = new QueryWrapper<>();
        gameInfoQueryWrapper.orderByDesc("id");
        return ResultVOUtil.success(gameInfoService.page(page,gameInfoQueryWrapper));
    }

    //获取最新的
    @PostMapping(value = "/oneByTypeName")
    public ResultVO oneByTypeName(@RequestParam String gameABName){
        return ResultVOUtil.success(gameInfoService.oneByTypeName(gameABName));
    }


}
