package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.entity.UserStar;
import com.shop.zaqiu.mapper.UserStarMapper;
import com.shop.zaqiu.service.IUserStarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.zaqiu.vo.StarGameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingran
 * @since 2019-12-03
 */
@Service
public class UserStarServiceImpl extends ServiceImpl<UserStarMapper, UserStar> implements IUserStarService {

    @Autowired
    private GameInfoServiceImpl gameInfoService;

    //获取用户的收藏列表
    public List<StarGameVo> getUserStarByUserId(Integer userId){
        QueryWrapper<UserStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);

        List<UserStar> stars = this.list(queryWrapper);
        if (stars.size()== 0) {
            return null;
        }
        List<StarGameVo> starGameVos = new ArrayList<>();
        stars.forEach(star->{
            GameInfo gameInfo = gameInfoService.getById(star.getGameId());
            StarGameVo starGameVo = new StarGameVo();
            starGameVo.setUserStar(star);
            starGameVo.setGameInfo(gameInfo);
            starGameVos.add(starGameVo);
        });
        return starGameVos;

    }

    //starSwitch true 收藏。false取消
    public boolean userStarSwitch(Integer userId,Integer gameId,boolean starSwitch){
        QueryWrapper<UserStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId).eq("gameId",gameId);

        if (starSwitch)
        {
            UserStar star = new UserStar();
            star.setUserId(userId);
            star.setGameId(gameId);
            return this.save(star);

        }
        else
        {
            return this.remove(queryWrapper);
        }
    }

    //查询是否收藏了某个游戏
    public UserStar userStar(Integer userId,Integer gameId){
        QueryWrapper<UserStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId).eq("gameId",gameId);
        return this.getOne(queryWrapper);
    }

}
