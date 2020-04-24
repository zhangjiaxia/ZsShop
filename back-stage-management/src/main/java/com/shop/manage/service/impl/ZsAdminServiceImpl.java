package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.mapper.ZsAdminMapper;
import com.shop.manage.service.IZsAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@Service
public class ZsAdminServiceImpl extends ServiceImpl<ZsAdminMapper, ZsAdmin> implements IZsAdminService {

    public ZsAdmin findAdmin(String username, String password){
        QueryWrapper<ZsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password", password);
        return this.getOne(queryWrapper);
    }

    public ZsAdmin findByAdminId(String adminId){
        QueryWrapper<ZsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",adminId);
        return this.getOne(queryWrapper);
    }

    //根据账号名查询管理员
    public ZsAdmin getAdminByName(String username) {
        QueryWrapper<ZsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }
}
