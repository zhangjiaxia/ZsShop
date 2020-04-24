package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsAdminPermission;
import com.shop.manage.entity.ZsPermission;
import com.shop.manage.mapper.ZsAdminPermissionMapper;
import com.shop.manage.service.IZsAdminPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
public class ZsAdminPermissionServiceImpl extends ServiceImpl<ZsAdminPermissionMapper, ZsAdminPermission> implements IZsAdminPermissionService {

    public Boolean removeByAdminId(Integer adminId){
        QueryWrapper<ZsAdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adminId", adminId);
        return this.remove(queryWrapper);
    }

    //批量插入用户菜单
    public Boolean insertBatch(Integer adminId, List<ZsPermission> menus){
        List<ZsAdminPermission> list = new ArrayList<>();
        for(ZsPermission zsPermission : menus){
            ZsAdminPermission zsAdminPermission = new ZsAdminPermission();
            zsAdminPermission.setPermissionId(zsPermission.getId());
            zsAdminPermission.setAdminId(adminId);
            list.add(zsAdminPermission);
        }
        Collection<ZsAdminPermission> adminPermissions = list;
        this.saveBatch(adminPermissions);
        return true;
    }

}
