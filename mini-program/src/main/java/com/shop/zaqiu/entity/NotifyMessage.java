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
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("notifyMessage")
@ApiModel(value="NotifyMessage对象", description="")
public class NotifyMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知内容")
    @TableField("notifyMsg")
    private String notifyMsg;

    @ApiModelProperty(value = "公告点击的链接")
    @TableField("notifyUrl")
    private String notifyUrl;

    @ApiModelProperty(value = "公告标题")
    private String title;

}
