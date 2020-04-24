package com.shop.zaqiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shop.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yingran
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wxApp")
@ApiModel(value="WxApp对象", description="")
public class WxApp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer version;

}
