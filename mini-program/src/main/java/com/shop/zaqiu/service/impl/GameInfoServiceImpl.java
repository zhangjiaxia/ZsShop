package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.mapper.GameInfoMapper;
import com.shop.zaqiu.service.IGameInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class GameInfoServiceImpl extends ServiceImpl<GameInfoMapper, GameInfo> implements IGameInfoService {

    public GameInfo oneByTypeName(String gameABName){
        QueryWrapper<GameInfo> gameInfoQueryWrappe = new QueryWrapper<>();
        gameInfoQueryWrappe.eq("gameABName",gameABName);
        return this.getOne(gameInfoQueryWrappe);
    }
}
