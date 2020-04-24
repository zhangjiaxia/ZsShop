package com.shop.zaqiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gameType")
@ApiModel(value="GameType对象", description="")
public class GameType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    @ApiModelProperty(value = "产品类型id")
    @TableField("productTypeId")
    private Integer productTypeId;

}
