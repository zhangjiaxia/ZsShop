package com.shop.zaqiu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.common.BaseController;
import com.shop.common.common.KeyUtil;
import com.shop.common.common.ResultVO;
import com.shop.common.common.ResultVOUtil;
import com.shop.zaqiu.dto.MainOrderDto;
import com.shop.zaqiu.dto.UserMainOrderDTO;
import com.shop.zaqiu.entity.SubOrder;
import com.shop.zaqiu.entity.User;
import com.shop.zaqiu.service.impl.CartServiceImpl;
import com.shop.zaqiu.service.impl.SubOrderServiceImpl;
import com.shop.zaqiu.service.impl.UserServiceImpl;
import com.shop.zaqiu.entity.MainOrder;
import com.shop.zaqiu.service.impl.MainOrderServiceImpl;
import com.shop.zaqiu.utils.HttpTool;
import com.shop.zaqiu.vo.UserCartVo;
import com.shop.zaqiu.vo.UserMainOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
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
@RequestMapping("/zaqiu/main-order")
@Slf4j
public class MainOrderController extends BaseController {

    @Autowired
    private MainOrderServiceImpl mainOrderService;

    @Autowired
    private SubOrderServiceImpl subOrderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private HttpTool httpTool;

    @PostMapping(value = "/save")
    public ResultVO Save(@RequestBody MainOrder mainOrder, HttpServletRequest request){
        //订单id
        User user = httpTool.GetUser(request);
        mainOrder.setUserId(user.getId());
        mainOrder.setOrderId(KeyUtil.genUniqueKey());
        boolean bool = mainOrderService.save(mainOrder);
        return ResultVOUtil.success(bool);
    }

    @GetMapping(value = "/removeById")
    @Transactional
    public ResultVO removeById(@RequestParam Integer orderId){

        subOrderService.removeByOrderId(orderId);

        boolean bool = mainOrderService.removeByOrderId(orderId);

        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/updateById")
    public ResultVO updateById(@RequestBody MainOrder mainOrder){
        boolean bool = mainOrderService.updateById(mainOrder);
        return ResultVOUtil.success(bool);
    }

    @PostMapping(value = "/getById")
    public ResultVO getById(@RequestParam Serializable id){
        MainOrder mainOrder = mainOrderService.getById(id);
        return ResultVOUtil.success(mainOrder);
    }

    @GetMapping(value = "/page")
    public ResultVO page(@RequestParam Integer index,@RequestParam int pages){
        IPage<MainOrder> page = new Page<>(index, pages);
        return ResultVOUtil.success(mainOrderService.page(page,null));
    }

    //生成订单
    @PostMapping(value = "/createOrder")
    public ResultVO createOrder(@RequestBody UserMainOrder userMainOrder, HttpServletRequest request){

        //订单id
        log.info("【userMainOrder:】"+userMainOrder.toString());

        User user = httpTool.GetUser(request);
        Integer orderId = KeyUtil.genOderId();
        BigDecimal totalSum = new BigDecimal("0");

        MainOrder mainOrder = new MainOrder();

        mainOrder.setUserId(user.getId());
        mainOrder.setOrderId(orderId.toString());
        mainOrder.setAddressId(Integer.parseInt(userMainOrder.getAddressId()));
        mainOrder.setMessage(userMainOrder.getMessage());

        for (int i = 0;i<userMainOrder.getUserCartVos().size();i++){
            BigDecimal oneCart = new BigDecimal("0.00");
            UserCartVo userCartVo = userMainOrder.getUserCartVos().get(i);
            BigDecimal index = new BigDecimal(userCartVo.getDevices().size());
            oneCart = oneCart.add(new BigDecimal(userCartVo.getGameInfo().getPrice().toString()));
            log.info("【游戏价格】"+userCartVo.getGameInfo().getPrice());

            oneCart = oneCart.multiply(index);

            totalSum = totalSum.add(oneCart);
        }

        mainOrder.setTotalSum(totalSum);
        log.info(mainOrder.toString()+"【mainOrder】");
        mainOrderService.save(mainOrder);
        //子订单
        userMainOrder.getUserCartVos().forEach(userCartVo -> {
            SubOrder subOrder = new SubOrder();
            subOrder.setOrderId(orderId.toString());
            subOrder.setGameId(userCartVo.getGameInfo().getId());
            List<String> strings = new ArrayList<>();
            userCartVo.getDevices().forEach(device -> {strings.add(device.getId().toString());});
            String jsonString = JSON.toJSONString(strings);
            log.info(jsonString+"【设备列表】");
            subOrder.setDevicedId(jsonString);
            subOrderService.save(subOrder);
            log.info("进入删除购物车操作");
            //加入购物车，结算进入订单页面的操作，删除购物车
            if(userCartVo.getCart() != null) {
                log.info(userCartVo.getCart().getId().toString());
                cartService.DelCartByID(userCartVo.getCart().getId());
            } else {
                //加入购物车，立即购买进入订单页面的操作，删除购物车
                mainOrderService.delCart(user, userCartVo);
            }
        });

        if (totalSum.toString().equals("0.00")) {
            log.info("进入支付完成操作，否则只返回订单ID");
            return ResultVOUtil.success(mainOrderService.OrderResult(orderId.toString()));
        }
        return ResultVOUtil.success(orderId.toString());
    }

    //获取订单详情
    @PostMapping(value = "/orderByPayState")
    public ResultVO orderByPayState(@RequestBody JSONObject jsonObject,HttpServletRequest request){
        Integer payState = jsonObject.getInteger("payState");
        User user = httpTool.GetUser(request);
        List<MainOrderDto> mainOrderDtos = mainOrderService.MainOrderDtos(payState,user.getId());
        return ResultVOUtil.success(mainOrderDtos);
    }

    //综合查询，根据账号，用户名，账号状态查询管理员列表
    @GetMapping(value = "/orderPageByMultipleQuery")
    public ResultVO orderPageByMultipleQuery(@RequestParam Integer index, @RequestParam int pages, @RequestParam(required=false) String orderId,
                                             @RequestParam(required=false) Integer payState, @RequestParam(required=false) String gameId,
                                             @RequestParam(required=false) String gameName, @RequestParam(required=false) String nick) {
        List<MainOrder> orders = mainOrderService.getOrderListByQuery(orderId, gameId, gameName, nick, payState);
        List<MainOrder> subOrders = new ArrayList<>();
        List<UserMainOrderDTO> userMainOrderDTOS = new ArrayList<>();

        IPage<UserMainOrderDTO> page = new Page<>(index, pages);
        page.setTotal(orders.size());
        subOrders = orders.subList(index == 1 ? 0 : (index - 1) * pages, orders.size() > index * pages ? index * pages : orders.size());
        for (MainOrder m : subOrders) {
            User u = userService.getById(m.getUserId());
            UserMainOrderDTO userMainOrderDTO = new UserMainOrderDTO(u, m);
            userMainOrderDTOS.add(userMainOrderDTO);
        }

        page.setRecords(userMainOrderDTOS);
        return ResultVOUtil.success(page);
    }

}
