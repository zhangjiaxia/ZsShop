package com.shop.zaqiu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
@ApiModel(value="Address对象", description="")
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "省")
    private String province;

    //是否默认收货(1为默认)
    @TableField(value = "isDefault", fill = FieldFill.DEFAULT)
    private int isDefault;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "用户自定义详细地址")
    @TableField("detailAddress")
    private String detailAddress;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "收货人手机号")
    @TableField("consigneePhone")
    private String consigneePhone;

    @ApiModelProperty(value = "用户Id")
    @TableField("userId")
    private Integer userId;

}
