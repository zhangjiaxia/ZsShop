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
 * 后台api管理员与角色表
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsAdminRole对象", description="后台api管理员与角色表")
public class ZsAdminRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID，引用角色表")
    @TableField("roleId")
    private Integer roleId;

    @ApiModelProperty(value = "管理员ID，引用管理员表")
    @TableField("adminId")
    private Integer adminId;

}
