package com.shop.zaqiu.entity;

import com.shop.common.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("AD")
@ApiModel(value="Ad对象", description="")
public class Ad extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告区域")
    private String area;

    @ApiModelProperty(value = "广告类型,广告类型 0--logo；1--主页；2--游戏场景,3--商城,4--程序识别码")
    @TableField("adType")
    private Integer adType;

    @ApiModelProperty(value = "游戏id")
    @TableField("gameId")
    private Integer gameId;

    @ApiModelProperty(value = "广告链接")
    @TableField("adUrl")
    private String adUrl;


}
