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
@ApiModel(value="User对象", description="")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户昵称")
    private String nick;

    @ApiModelProperty(value = "用户手机")
    private String phone;

    @ApiModelProperty(value = "用户微信openId")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "用户微信unionId")
    @TableField("unionId")
    private String unionId;

    @ApiModelProperty(value = "用户等级")
    private Integer grade;

    @ApiModelProperty(value = "用户性别,0女1男")
    private Integer sex;

    @ApiModelProperty(value = "用户地区")
    private String area;

    @ApiModelProperty(value = "用户头像链接")
    @TableField("headUrl")
    private String headUrl;


}
