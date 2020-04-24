package com.shop.manage.controller;

import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.manage.entity.ZsDepartment;
import com.shop.manage.service.impl.ZsDepartmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/zaqiumanagement/zs-department")
@Slf4j
public class ZsDepartmentController extends BaseController {

    @Autowired
    private ZsDepartmentServiceImpl zsDepartmentService;

    @GetMapping("/getAllDepartment")
    public ResultVO getAllDepartment(){
        List<ZsDepartment> zsDepartment = zsDepartmentService.findDepartmentList();
        return ResultVOUtil.success(zsDepartment);
    }
}
