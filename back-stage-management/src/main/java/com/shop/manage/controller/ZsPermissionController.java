package com.shop.manage.controller;

import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.manage.entity.ZsPermission;
import com.shop.manage.service.impl.ZsAdminPermissionServiceImpl;
import com.shop.manage.service.impl.ZsPermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/zaqiumanagement/zs-permission")
public class ZsPermissionController extends BaseController {

    @Autowired
    ZsPermissionServiceImpl zsPermissionService;

    @Autowired
    ZsAdminPermissionServiceImpl zsAdminPermissionService;

    @GetMapping(value = "/getMenus")
    public ResultVO getMenus(@RequestParam Integer adminId){
        List<ZsPermission> menus = zsPermissionService.findMenusById(adminId);
        return ResultVOUtil.success(menus);
    }

    @Transactional
    @GetMapping(value = "/setMenus")
    public ResultVO setMenus(@RequestParam Integer adminId, @RequestParam String nameStr){
        //查出要授权的菜单列表
        List<ZsPermission> menus = zsPermissionService.findMenusByNames(nameStr);
        //移除该管理员下的所有菜单项
        zsAdminPermissionService.removeByAdminId(adminId);
        //插入管理员菜单中间表
        Boolean result = zsAdminPermissionService.insertBatch(adminId, menus);
        return ResultVOUtil.success(result);
    }
}
