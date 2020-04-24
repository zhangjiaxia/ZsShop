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
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("productType")
@ApiModel(value="ProductType对象", description="")
public class ProductType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品类型")
    @TableField("productType")
    private Integer productType;

    @ApiModelProperty(value = "产品类型名字")
    @TableField("className")
    private String className;

    @ApiModelProperty(value = "0 虚拟物体1 实物")
    @TableField("productClass")
    private Integer productClass;

}
