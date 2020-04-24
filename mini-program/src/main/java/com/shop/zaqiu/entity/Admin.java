package com.shop.zaqiu.entity;

import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员名单
 * </p>
 *
 * @author yingran
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Admin对象", description="管理员名单")
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "openid微信的")
    private String openid;

    @ApiModelProperty(value = "管理员名字")
    private String name;

}
