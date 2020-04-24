package com.shop.common.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author : Ran
 * Project: shop
 * Package: com.shop.zaqiu.entity
 * @date : 2019/10/23 22:45
 */
@Data
public class BaseEntity {
    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updateTime", update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date updateTime;

}