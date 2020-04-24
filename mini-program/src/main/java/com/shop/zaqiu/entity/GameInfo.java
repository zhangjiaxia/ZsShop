package com.shop.zaqiu.entity;

import java.math.BigDecimal;
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
@TableName("gameInfo")
@ApiModel(value="GameInfo对象", description="")
public class GameInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "游戏名")
    @TableField("gameName")
    private String gameName;

    @TableField("gameVersion")
    private Integer gameVersion;

    @ApiModelProperty(value = "游戏标题")
    @TableField("nameTitle")
    private String nameTitle;

    @ApiModelProperty(value = "游戏包名")
    @TableField("gameABName")
    private String gameABName;

    @ApiModelProperty(value = "游戏价格")
    private BigDecimal price;

    @ApiModelProperty(value = "游戏介绍")
    private String content;

    @ApiModelProperty(value = "游戏分类Id")
    @TableField("typeId")
    private Integer typeId;

    @ApiModelProperty(value = "游戏封面图片")
    @TableField("imageUrl")
    private String imageUrl;

    @ApiModelProperty(value = "游戏小图")
    @TableField("iconUrl")
    private String iconUrl;

    @ApiModelProperty(value = "游戏视频")
    @TableField("videoUrl")
    private String videoUrl;

    @ApiModelProperty(value = "游戏下载链接")
    @TableField("downUrl")
    private String downUrl;

    @ApiModelProperty(value = "游戏语言")
    private String language;

    @ApiModelProperty(value = "游戏介绍html源码")
    @TableField("htmlCode")
    private String htmlCode;

    @ApiModelProperty(value = "游戏销量")
    @TableField("sale")
    private Integer sale;

    @ApiModelProperty(value = "游戏大分类")
    @TableField("productType")
    private Integer productType;

    @ApiModelProperty(value = "游戏质量")
    @TableField("gameQuality")
    private Integer gameQuality;

}
