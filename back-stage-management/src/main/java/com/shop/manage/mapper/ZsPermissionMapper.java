package com.shop.manage.mapper;

import com.shop.manage.entity.ZsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
public interface ZsPermissionMapper extends BaseMapper<ZsPermission> {

    //查询管理员拥有的菜单列表
    @Select("SELECT * from zs_permission where id in (SELECT permissionId from zs_admin_permission where adminId = #{adminId})")
    List<ZsPermission> getAdminMenuList(int adminId);

    //根据唯一name查询菜单列表
    @Select("SELECT * from zs_permission where name in (#{nameStr})")
    List<ZsPermission> getAuthMenuList(String nameStr);
}
