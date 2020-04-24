package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsAdminPermission;
import com.shop.manage.entity.ZsPermission;
import com.shop.manage.mapper.ZsAdminPermissionMapper;
import com.shop.manage.mapper.ZsPermissionMapper;
import com.shop.manage.service.IZsPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@Service
public class ZsPermissionServiceImpl extends ServiceImpl<ZsPermissionMapper, ZsPermission> implements IZsPermissionService {
    @Autowired
    ZsPermissionMapper zsPermissionMapper;

    //根据管理员ID查询菜单列表
    public List<ZsPermission> findMenusById(Integer adminId){
        return zsPermissionMapper.getAdminMenuList(adminId);
    }

    //根据唯一name查询菜单列表
    public List<ZsPermission> findMenusByNames(String nameStr){
        String[] str = nameStr.split(",");
        //将字符串数组转换成集合
        List list = Arrays.asList(str);
        List<ZsPermission> menus = this.list(new QueryWrapper<ZsPermission>().lambda().in(ZsPermission::getName, list));
        return menus;
    }
}
