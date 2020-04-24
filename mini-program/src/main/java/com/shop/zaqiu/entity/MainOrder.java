package com.shop.zaqiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
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
@TableName("mainOrder")
@ApiModel(value="MainOrder对象", description="")
public class MainOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("orderId")
    private String orderId;

    @ApiModelProperty(value = "地址Id")
    @TableField("addressId")
    private Integer addressId;

    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "留言")
    private String message;

    @ApiModelProperty(value = "支付状态")
    @TableField("payState")
    private Integer payState;

    @ApiModelProperty(value = "结算金额")
    @TableField("totalSum")
    private BigDecimal totalSum;

}
