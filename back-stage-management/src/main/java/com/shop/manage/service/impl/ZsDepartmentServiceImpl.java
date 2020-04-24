package com.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manage.entity.ZsDepartment;
import com.shop.manage.mapper.ZsDepartmentMapper;
import com.shop.manage.service.IZsDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaixa
 * @since 2019-12-11
 */
@Service
public class ZsDepartmentServiceImpl extends ServiceImpl<ZsDepartmentMapper, ZsDepartment> implements IZsDepartmentService {

    //查询所有部门列表
    public List<ZsDepartment> findDepartmentList(){
        QueryWrapper<ZsDepartment> adQueryWrapper = new QueryWrapper<>();
        return this.list(adQueryWrapper);
    }
}
