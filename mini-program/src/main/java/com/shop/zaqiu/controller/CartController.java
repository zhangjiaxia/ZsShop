package com.shop.zaqiu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.entity.Device;
import com.shop.zaqiu.entity.GameInfo;
import com.shop.zaqiu.service.impl.DeviceServiceImpl;
import com.shop.zaqiu.service.impl.GameInfoServiceImpl;
import com.shop.zaqiu.entity.Cart;
import com.shop.zaqiu.service.impl.CartServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import com.shop.zaqiu.vo.UserCartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingran
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/zaqiu/cart")
@Slf4j
public class CartController extends BaseController {
    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private GameInfoServiceImpl gameInfoService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody Cart cart, HttpServletRequest request){

        cart.setUserId(httpTool.GetUser(request).getId());

        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("userId",httpTool.GetUser(request).getId())
                .eq("gameId",cart.getGameId());

        List<Cart> carts = cartService.list(cartQueryWrapper);

        if (carts.size() >= 1){
            cart.setId(carts.get(0).getId());
           return ResultVOUtil.success(cartService.saveOrUpdate(cart));
        }

        cart.setDeviceId(cart.getDeviceId());
        cart.setGameId(cart.getGameId());

        boolean bool = cartService.save(cart);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/removeById")
    public ResultVO removeById(@RequestParam int id){
        boolean bool = cartService.removeById(id);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody Cart cart){
        boolean bool = cartService.updateById(cart);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        Cart cart = cartService.getById(id);
        return ResultVOUtil.success(cart);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<Cart> page = new Page<>(index, pages);
        return ResultVOUtil.success(cartService.page(page,null));
    }

    @GetMapping(value = "/listUserCart")
    public ResultVO listUserCard(HttpServletRequest request){

        List<UserCartVo> userCartVos = new ArrayList<>();

        List<Cart> carts = cartService.listUserCard(httpTool.GetUser(request).getId());

        carts.forEach(cart -> {

            UserCartVo userCartVo = new UserCartVo();
            List<Device> devices = new ArrayList<>();

            GameInfo gameInfo = gameInfoService.getById(cart.getGameId());
            List<String> strings = JSON.parseArray(cart.getDeviceId(),String.class);

            for (int i = 0;i < strings.size(); i++){
                Device device = deviceService.getById(Integer.parseInt(strings.get(i)));
                devices.add(device);
            }

            userCartVo.setCart(cart);
            userCartVo.setDevices(devices);
            userCartVo.setGameInfo(gameInfo);

            userCartVos.add(userCartVo);

        });

        return ResultVOUtil.success(userCartVos);
    }

    @PostMapping(value = "/batchDelCartIds")
    public ResultVO batchDelCartIds(@RequestBody List<Integer> ids){
        return ResultVOUtil.success(cartService.removeByIds(ids));
    }

}
