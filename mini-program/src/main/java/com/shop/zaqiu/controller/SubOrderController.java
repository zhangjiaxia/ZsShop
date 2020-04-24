package com.shop.zaqiu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.dto.SubOrderDto;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import com.shop.zaqiu.service.impl.GameInfoServiceImpl;
import com.shop.zaqiu.entity.SubOrder;
import com.shop.zaqiu.service.impl.SubOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/zaqiu/sub-order")
public class SubOrderController extends BaseController {

    @Autowired
    private SubOrderServiceImpl subOrderService;

    @Autowired
    private GameInfoServiceImpl gameInfoService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody SubOrder subOrder){
        boolean bool = subOrderService.save(subOrder);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = subOrderService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody SubOrder subOrder){
        boolean bool = subOrderService.updateById(subOrder);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        SubOrder subOrder = subOrderService.getById(id);
        return ResultVOUtil.success(subOrder);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<SubOrder> page = new Page<>(index, pages);
        return ResultVOUtil.success(subOrderService.page(page,null));
    }

    //根据订单编号查询子订单详情
    @GetMapping(value = "/getSubOrderDetail")
    public ResultVO getSubOrderDetail(@RequestParam String orderId){
        List<SubOrder> subOrders = subOrderService.getListSubOrder(orderId);
        List<SubOrderDto> subOrderDtoList = new ArrayList<>();
        for (SubOrder subOrder : subOrders) {
            GameInfo gameInfo = gameInfoService.getById(subOrder.getGameId());
            List<String> lsOneDevice = JSON.parseArray(subOrder.getDevicedId(), String.class);
            List<Device> devices = deviceService.findByIds(lsOneDevice);
            SubOrderDto subOrderDto = new SubOrderDto(subOrder, gameInfo, devices);
            subOrderDtoList.add(subOrderDto);
        }
        return ResultVOUtil.success(subOrderDtoList);
    }

}
