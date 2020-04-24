package com.shop.zaqiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.zaqiu.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author : Ran
 * Project: shop
 * Package: com.yingran.shop.zaqiu.dao
 * @date : 2019/12/4 13:39
 */
@Mapper
public interface AddressDao extends BaseMapper<Address> {
    @Update("update address set isDefault=0 where userId = #{userId}")
    void UpdateToDefault(Integer userId);
}
