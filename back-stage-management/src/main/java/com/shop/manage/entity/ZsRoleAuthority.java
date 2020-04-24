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
 * 后台api角色与权限表
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsRoleAuthority对象", description="后台api角色与权限表")
public class ZsRoleAuthority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID，引用角色表")
    @TableField("roleId")
    private Integer roleId;

    @ApiModelProperty(value = "权限ID，引用权限表")
    @TableField("authorityId")
    private Integer authorityId;

}
