package com.shop.manage.service.impl;

import com.shop.manage.entity.ZsAdminAuthority;
import com.shop.manage.mapper.ZsAdminAuthorityMapper;
import com.shop.manage.service.IZsAdminAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台api管理员与权限表，用于在已有角色的权限集合里增删对应的权限 服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Service
public class ZsAdminAuthorityServiceImpl extends ServiceImpl<ZsAdminAuthorityMapper, ZsAdminAuthority> implements IZsAdminAuthorityService {

}
