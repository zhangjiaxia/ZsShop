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
 * 
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsAdminPermission对象", description="")
public class ZsAdminPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID，引用权限表")
    @TableField("permissionId")
    private Integer permissionId;

    @ApiModelProperty(value = "用户ID，引用用户表")
    @TableField("adminId")
    private Integer adminId;

}
