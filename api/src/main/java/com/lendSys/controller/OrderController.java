package com.lendSys.controller;

import com.lendSys.entity.Cart;
import com.lendSys.entity.Orders;
import com.lendSys.service.OrderService;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.cartsOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value  = "get orders", notes = "get orders by name")
    @GetMapping(value="/{current}/{size}")
    public ResultVo getAllByName(@PathVariable String current,
                                 @PathVariable String size,
                                 @RequestParam String username){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return orderService.getAllOrder(username,curr,s);
    }

    @ApiOperation(value  = "add orders", notes = "add orders")
    @PostMapping(value = "/add")
    public ResultVo addToOrder(@RequestBody cartsOrderVo cartsordervo){
        CartVo[] carts = cartsordervo.getCarts();
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderRemark(cartsordervo.getOrderRemark());
        orderVO.setBorrowlength(cartsordervo.getBorrowlength());
        return orderService.addToOrder(carts,orderVO);
    }

    @ApiOperation(value  = "get return orders", notes = "get return orders")
    @GetMapping(value="/getReturn")
    public ResultVo getReturnOrder(@RequestParam String username){
        return orderService.getReturnOrder(username);
    }

    @ApiOperation(value  = "get approval orders", notes = "get approval orders and shown by pages")
    @GetMapping(value="/approvalOrder/{current}/{size}")
    public ResultVo getApprovalOrder(@PathVariable String current,
                                   @PathVariable String size){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return orderService.getApprovalOrder(curr,s);
    }

    @ApiOperation(value  = "set return date", notes = "set return date for orders")
    @PostMapping(value = "/endDate/")
    public ResultVo setRentOrder(@RequestBody Orders orders){
        return orderService.setRentOrder(orders);
    }

    @ApiOperation(value  = "approve orders by name", notes = "department leader needs to approve orders")
    @GetMapping(value = "/approveRentOrder/")
    public ResultVo approveRentOrder(@RequestParam("username") String username){
        return orderService.approveRentOrder(username);
    }

    @ApiOperation(value  = "approve orders", notes = "get all approve orders")
    @PostMapping(value = "/approve/")
    public ResultVo approveOrder(@RequestBody Orders orders){
        return orderService.approveOrder(orders);
    }

    @ApiOperation(value  = "return orders", notes = "return orders")
    @PostMapping(value = "/returnOrder/")
    public ResultVo returnOrder(@RequestBody Orders orders){
        return orderService.returnOrder(orders);
    }

    @ApiOperation(value  = "select orders by status", notes = "select orders by different status")
    @GetMapping(value = "/selectByStatus/{current}/{size}")
    public ResultVo selectByStatus(@RequestParam("username") String username,
                                   @RequestParam("status") String status,
                                   @PathVariable String current,
                                   @PathVariable String size){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        int orderStatus = Integer.parseInt(status);
        return orderService.selectByStatus(username,orderStatus,curr,s);
    }

    @ApiOperation(value  = "delete orders", notes = "delete orders")
    @PostMapping(value = "/delete/")
    public ResultVo deleteOrder(@RequestBody Orders orders){
        return orderService.deleteOrder(orders);
    }

}
