package com.shop.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZsAdmin对象", description="")
public class ZsAdmin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户名")
    @TableField("fullName")
    private String fullName;

    @ApiModelProperty(value = "身份证号码")
    @TableField("idCard")
    private String idCard;

    @ApiModelProperty(value = "头像")
    @TableField("headUrl")
    private String headUrl;

    @ApiModelProperty(value = "用户职位")
    private String role;

    @ApiModelProperty(value = "部门ID，引用部门表")
    @TableField("departmentId")
    private Integer departmentId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Boolean status;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("loginTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date loginTime;


}
