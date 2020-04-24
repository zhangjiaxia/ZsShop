package com.shop.manage.mapper;

import com.shop.manage.entity.ZsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 后台api角色表 Mapper 接口
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
public interface ZsRoleMapper extends BaseMapper<ZsRole> {

    //查询管理员拥有的角色列表
    @Select("SELECT * FROM zs_role where id in (SELECT roleId FROM zs_admin_role where adminId = #{adminId})")
    List<ZsRole> getRolesByAdmin(int adminId);
}
