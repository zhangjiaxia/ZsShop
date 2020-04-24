package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.mapper.UserMapper;
import com.shop.zaqiu.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public User getOneByOpenId(String openId){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        return this.getOne(wrapper);
    }



}
