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
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("userStar")
@ApiModel(value="UserStar对象", description="")
public class UserStar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "游戏Id")
    @TableField("gameId")
    private Integer gameId;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

}
