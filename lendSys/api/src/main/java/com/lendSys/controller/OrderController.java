package com.lendSys.controller;

import com.lendSys.entity.Cart;
import com.lendSys.entity.Orders;
import com.lendSys.service.OrderService;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.cartsOrderVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
@Api(value = "User Order", tags = "User Order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping(value="/{current}/{size}")
    public ResultVo getAllByName(@PathVariable String current,
                                 @PathVariable String size,
                                 @RequestParam String username){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return orderService.getAllOrder(username,curr,s);
    }

    @PostMapping(value = "/add")
    public ResultVo addToOrder(@RequestBody cartsOrderVo cartsordervo){
        CartVo[] carts = cartsordervo.getCarts();
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderRemark(cartsordervo.getOrderRemark());
        orderVO.setBorrowlength(cartsordervo.getBorrowlength());
        return orderService.addToOrder(carts,orderVO);
    }

    @GetMapping(value="/getReturn")
    public ResultVo getReturnOrder(@RequestParam String username){
        return orderService.getReturnOrder(username);
    }

}
