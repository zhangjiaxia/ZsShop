package com.shop.zaqiu.entity;

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
 * @author yingran
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Cart对象", description="")
public class Cart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "终端Id")
    @TableField("deviceId")
    private String deviceId;

    @ApiModelProperty(value = "游戏Id")
    @TableField("gameId")
    private Integer gameId;



}
