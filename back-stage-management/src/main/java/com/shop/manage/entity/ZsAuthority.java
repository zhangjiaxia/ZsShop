package com.shop.manage.entity;

import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台api权限表
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsAuthority对象", description="后台api权限表")
public class ZsAuthority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "api的url")
    private String api;

    @ApiModelProperty(value = "api的简介")
    private String description;


}
