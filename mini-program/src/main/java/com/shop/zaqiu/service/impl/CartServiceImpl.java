package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.Cart;
import com.shop.zaqiu.mapper.CartMapper;
import com.shop.zaqiu.service.ICartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private CartServiceImpl cartService;

    public List<Cart> listUserCard(Integer userId){
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("userId", userId);
        return this.list(cartQueryWrapper);
    }

    //根据用户id，以及游戏id获取购物车
    public Cart getCartByUserIdAndGameId(Integer userId, Integer gameId){
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("userId",userId).eq("gameId",gameId);
        Cart cart = getOne(cartQueryWrapper);
        return cart;
    }

    //根据用户id，以及游戏id删除购物车
    public Boolean DelCart(Integer gameId,String deviceId){
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.like("deviceId",deviceId).eq("gameId",gameId);
        Cart cart = getOne(cartQueryWrapper);
        if (cart != null){
            return cartService.removeById(cart.getId());
        }
        return false;
    }

    //根据购物车ID删除购物车
    public Boolean DelCartByID(Integer cartId){
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("id", cartId);
        Cart cart = getOne(cartQueryWrapper);
        if (cart != null){
            return cartService.removeById(cart.getId());
        }
        return false;
    }
}
