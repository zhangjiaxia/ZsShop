package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsAuthority;
import com.shop.manage.entity.ZsRole;
import com.shop.manage.mapper.ZsAuthorityMapper;
import com.shop.manage.service.IZsAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台api权限表 服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Service
public class ZsAuthorityServiceImpl extends ServiceImpl<ZsAuthorityMapper, ZsAuthority> implements IZsAuthorityService {

    @Autowired
    private ZsAuthorityMapper zsAuthorityMapper;

    //根据角色Id查询对应的权限，即API方法
    public List<ZsAuthority> getAuthoritiesByRole(Integer roleId) {
        return zsAuthorityMapper.getAuthoritiesByRole(roleId);
    }
}
