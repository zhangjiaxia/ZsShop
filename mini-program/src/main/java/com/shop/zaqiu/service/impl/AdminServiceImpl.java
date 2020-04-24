package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.Admin;
import com.shop.zaqiu.mapper.AdminMapper;
import com.shop.zaqiu.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员名单 服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-11-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    public Admin findByOpenId(String openId){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        return this.getOne(queryWrapper);
    }
}
