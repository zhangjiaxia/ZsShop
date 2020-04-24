package com.shop.manage.mapper;

import com.shop.manage.entity.ZsAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.entity.ZsRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 后台api权限表 Mapper 接口
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
public interface ZsAuthorityMapper extends BaseMapper<ZsAuthority> {

    //查询某角色拥有的权限列表
    @Select("SELECT * FROM zs_authority where id in (SELECT authorityId FROM zs_role_authority where roleId = #{roleId})")
    List<ZsAuthority> getAuthoritiesByRole(int roleId);
}
