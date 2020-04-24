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
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("subOrder")
@ApiModel(value="SubOrder对象", description="")
public class SubOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = " 订单Id")
    @TableField("orderId")
    private String orderId;

    @ApiModelProperty(value = "游戏Id")
    @TableField("gameId")
    private Integer gameId;

    @ApiModelProperty(value = "终端Id")
    @TableField("devicedId")
    private String devicedId;


}
