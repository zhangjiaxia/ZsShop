package com.shop.zaqiu.controller;


import com.alibaba.fastjson.JSONObject;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.ProductType;
import com.shop.zaqiu.service.impl.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/zaqiu/product-type")
public class ProductTypeController extends BaseController {

    @Autowired
    private ProductTypeServiceImpl productTypeService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody ProductType productType){
        return ResultVOUtil.success(productTypeService.save(productType));
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInteger("id");
        Boolean bool =  productTypeService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody ProductType productType){
        return ResultVOUtil.success(productTypeService.saveOrUpdate(productType));
    }

    @GetMapping("/all")
    public ResultVO All(){
        return ResultVOUtil.success(productTypeService.list());
    }

}
