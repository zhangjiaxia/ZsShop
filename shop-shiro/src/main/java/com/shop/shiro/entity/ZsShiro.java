package com.shop.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author CrazyJay
 * @Date 2019/3/31 10:56
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsShiro对象", description="后台api,shiro的token")
public class ZsShiro extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID，引用管理员表")
    @TableField("adminId")
    private Integer adminId;

    @ApiModelProperty(value = "token")
    @TableField("token")
    private String token;

    @ApiModelProperty(value = "token过期时间")
    @TableField("expireTime")
    private Date expireTime;
}
