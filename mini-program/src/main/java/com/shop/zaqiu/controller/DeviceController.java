package com.shop.zaqiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.common.enums.GradeEnum;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.mapper.DeviceMapper;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
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
@RequestMapping("/zaqiu/device")
@Slf4j
public class DeviceController extends BaseController {
    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private HttpTool httpTool;

    @Autowired
    private DeviceMapper deviceMapper;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody Device device){
        boolean bool = deviceService.save(device);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = deviceService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody Device device){
        boolean bool = deviceService.updateById(device);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        Device device = deviceService.getById(id);
        return ResultVOUtil.success(device);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<Device> page = new Page<>(index, pages);
        return ResultVOUtil.success(deviceService.page(page,null));
    }


    //管理员入库
    @PostMapping(value = "/adminSaveDevice")
    public ResultVO adminSaveDevice(@RequestBody Device device,HttpServletRequest request){
        User user = httpTool.GetUser(request);
        if (user != null && user.getGrade()== GradeEnum.ADMIN.getCode()){

             Device d = deviceService.findBySerialNumber(device.getSerialNumber());

             if (d == null){
                 return ResultVOUtil.success(deviceService.save(device));
             }
             else {
                 return ResultVOUtil.success("序列号已存在");
             }

        }
        return ResultVOUtil.success("没有权限！");
    }



}
