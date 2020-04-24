package com.shop.zaqiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.entity.UserStar;
import com.shop.zaqiu.service.impl.UserStarServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/zaqiu/user-star")
public class UserStarController extends BaseController {

    @Autowired
    private UserStarServiceImpl starService;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody UserStar userStar){
        boolean bool = starService.save(userStar);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = starService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody UserStar userStar){
        boolean bool = starService.updateById(userStar);
        return ResultVOUtil.success(bool);
    }

    @GetMapping(value = "/getById")
    public ResultVO getById(@RequestParam Integer id){
        UserStar userStar= starService.getById(id);
        return ResultVOUtil.success(userStar);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<UserStar> page = new Page<>(index, pages);
        return ResultVOUtil.success(starService.page(page,null));
    }

    //获取用户收藏的游戏列表
    @GetMapping(value = "/getUserStarList")
    public ResultVO getUserStarList(HttpServletRequest request){
        User user = httpTool.GetUser(request);
        return ResultVOUtil.success(starService.getUserStarByUserId(user.getId()));
    }

    //取消或收藏
    @GetMapping("/UserStarSwitch")
    public ResultVO UserStarSwitch(@RequestParam Integer gameId,@RequestParam boolean starSwitch,HttpServletRequest request){
        User user = httpTool.GetUser(request);
        return ResultVOUtil.success(starService.userStarSwitch(user.getId(),gameId,starSwitch));
    }

    @GetMapping("/getStarOne")
    public ResultVO getStarOne(@RequestParam Integer gameId,HttpServletRequest request){
        User user = httpTool.GetUser(request);
        return ResultVOUtil.success(starService.userStar(user.getId(),gameId));
    }

    @PostMapping(value = "/batchDelStar")
    public ResultVO batchDelStarIds(@RequestBody List<Integer> ids){
        return ResultVOUtil.success(starService.removeByIds(ids));
    }

}
