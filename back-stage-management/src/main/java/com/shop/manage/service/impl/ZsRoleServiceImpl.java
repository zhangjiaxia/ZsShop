package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.entity.ZsRole;
import com.shop.manage.mapper.ZsPermissionMapper;
import com.shop.manage.mapper.ZsRoleMapper;
import com.shop.manage.service.IZsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台api角色表 服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Service
public class ZsRoleServiceImpl extends ServiceImpl<ZsRoleMapper, ZsRole> implements IZsRoleService {

    @Autowired
    ZsRoleMapper zsRoleMapper;

    //根据管理员Id查询对应的角色
    public List<ZsRole> getRolesByAdmin(Integer adminId) {
        return zsRoleMapper.getRolesByAdmin(adminId);
    }
}
