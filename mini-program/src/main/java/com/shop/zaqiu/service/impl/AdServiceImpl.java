package com.shop.zaqiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.zaqiu.entity.Ad;
import com.shop.zaqiu.mapper.AdMapper;
import com.shop.zaqiu.service.IAdService;
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
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    public List<Ad> findByAreaAdnType(String area, String type){
        QueryWrapper<Ad> adQueryWrapper = new QueryWrapper<>();
        adQueryWrapper.eq("area",area).eq("adType",type);

        return this.list(adQueryWrapper);
    }
}
