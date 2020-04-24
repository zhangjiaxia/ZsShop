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
@ApiModel(value="ZsPermission对象", description="")
public class ZsPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限名")
    private String name;

    @ApiModelProperty(value = "权限标题")
    private String title;

    @ApiModelProperty(value = "权限图标")
    private String icon;

    @ApiModelProperty(value = "权限路由重定向，可为空")
    private String redirect;

    @ApiModelProperty(value = "权限路由")
    private String path;

    @ApiModelProperty(value = "额外权限，如控制增删改查级别的权限")
    private String meta;

    @ApiModelProperty(value = "是否为基础权限")
    @TableField("isBase")
    private Boolean isBase;
}
