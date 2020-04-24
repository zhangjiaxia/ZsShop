package com.shop.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台api管理员与权限表，用于在已有角色的权限集合里增删对应的权限
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsAdminAuthority对象", description="后台api管理员与权限表，用于在已有角色的权限集合里增删对应的权限")
public class ZsAdminAuthority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID，引用权限表")
    @TableField("authorityId")
    private Integer authorityId;

    @ApiModelProperty(value = "管理员ID，引用管理员表")
    @TableField("adminId")
    private Integer adminId;


}
