package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.dao.AddressDao;
import com.shop.zaqiu.entity.Address;
import com.shop.zaqiu.mapper.AddressMapper;
import com.shop.zaqiu.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private AddressDao addressDao;

    public boolean SaveDefault(Address address){
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("userId",address.getUserId());

        List<Address> addresses = this.list(addressQueryWrapper);

        if (addresses.size() == 0){
            address.setIsDefault(1);
            return save(address);
        }
        else {
            if (address.getIsDefault()==1)
            {
                addressDao.UpdateToDefault(address.getUserId());
            }

        }
        return save(address);
    }

    public Address getDefault(Integer userId){

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("isDefault",1).eq("userId",userId);
        return  getOne(addressQueryWrapper);

    }
}
