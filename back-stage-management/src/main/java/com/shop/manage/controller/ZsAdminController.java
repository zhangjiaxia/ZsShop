package com.shop.manage.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.entity.ZsPermission;
import com.shop.manage.service.impl.ZsAdminServiceImpl;
import com.shop.manage.service.impl.ZsPermissionServiceImpl;
import com.shop.manage.utils.ManageHttpTool;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/zaqiumanagement/zs-admin")
public class ZsAdminController extends BaseController {

    @Autowired
    private ZsAdminServiceImpl zsAdminService;

    @Autowired
    ZsPermissionServiceImpl zsPermissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ManageHttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody ZsAdmin admin){
        boolean bool = zsAdminService.saveOrUpdate(admin);
        return ResultVOUtil.success(bool);
    }

    @RequiresRoles({"vip"}) //没有的话 AuthorizationException
    @GetMapping(value = "/getInfo")
    public ResultVO getInfo(HttpServletRequest request) {
        ZsAdmin admin = httpTool.GetAdmin(request);
        List<ZsPermission> menus = zsPermissionService.findMenusById(admin.getId());
        JSONObject info = new JSONObject();
        info.put("baseInfo", admin);
        info.put("menus", menus);
        return ResultVOUtil.success(info);
    }

    @GetMapping(value = "/delAdmin")
    public ResultVO delAdmin(@RequestParam Integer adminId){
        Boolean result = zsAdminService.removeById(adminId);
        return ResultVOUtil.success(result);
    }

    @GetMapping(value = "/adminPage")
    public ResultVO adminPage(@RequestParam Integer index,@RequestParam int pages){
        IPage<ZsAdmin> page = new Page<>(index, pages);
        return ResultVOUtil.success(zsAdminService.page(page,null));
    }

    //综合查询，根据账号，用户名，账号状态查询管理员列表
    @GetMapping(value = "/adminPageByMultipleQuery")
    public ResultVO adminPageByMultipleQuery(@RequestParam Integer index,@RequestParam int pages, @RequestParam(required=false) String fullName,
                                             @RequestParam(required=false) Integer status, @RequestParam(required=false) String username){
        IPage<ZsAdmin> page = new Page<>(index, pages);
        QueryWrapper<ZsAdmin> zsAdminQueryWrapper = new QueryWrapper<>();
        if(status != null) {
            zsAdminQueryWrapper.eq("status",status);
        }
        if(!fullName.equals("")) {
            zsAdminQueryWrapper.like("fullName",fullName);
        }
        if(!username.equals("")) {
            zsAdminQueryWrapper.like("username",username);
        }
        return ResultVOUtil.success(zsAdminService.page(page, zsAdminQueryWrapper));
    }
}
