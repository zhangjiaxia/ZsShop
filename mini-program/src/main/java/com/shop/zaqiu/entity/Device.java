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
@ApiModel(value="Device对象", description="")
public class Device extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "摄像头代码")
    @TableField("cameraCode")
    private String cameraCode;

    @ApiModelProperty(value = "显示器代码")
    @TableField("displayCode")
    private String displayCode;

    @ApiModelProperty(value = "Pc代码")
    @TableField("pcCode")
    private String pcCode;

    @ApiModelProperty(value = "体感代码")
    @TableField("kinectCode")
    private String kinectCode;


    @ApiModelProperty(value = "用户的客户端序列号")
    @TableField("serialNumber")
    private String serialNumber;


    @ApiModelProperty(value = "用户的客户端序列备注")
    @TableField("customDeviceName")
    private String customDeviceName;

    @ApiModelProperty(value = "用户的客户端序列备注")
    @TableField("Ad")
    private Integer Ad;


}
