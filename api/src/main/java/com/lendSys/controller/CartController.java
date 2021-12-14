package com.lendSys.controller;

import com.lendSys.entity.Cart;
import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.service.cartService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Api(value = "User Cart", tags = "User Cart")
@CrossOrigin
public class CartController {
    @Resource
    private cartService carts;

    @ApiOperation(value  = "get usr cart", notes = "get usr cart by pages")
    @GetMapping(value = "/{current}/{size}")
    public ResultVo getCartProduct(@PathVariable String current,
                                   @PathVariable String size,
                                   @RequestParam("username") String username) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return carts.getAllCart(curr,s,username);
    }

    @ApiOperation(value  = "add usr cart", notes = "add usr cart")
    @PostMapping(value = "/add")
    public ResultVo addCart(@RequestBody Map<String,String> map){
        int productid = Integer.parseInt(map.get("productid"));
        String userName = map.get("username");
        int cartNum = Integer.parseInt(map.get("cartNum"));
        Cart cart = new Cart();
        cart.setProductid(productid);
        cart.setCartNum(cartNum);
        cart.setCartTime(new Date());
        return carts.addCart(cart,userName);
    }

    @ApiOperation(value  = "delete usr cart", notes = "delete usr cart")
    @PostMapping(value = "/remove")
    public ResultVo removeCart(@RequestBody List<String> ids){
        return carts.removeCart(ids);
    }

    @ApiOperation(value  = "get usr cart", notes = "get usr cart")
    @GetMapping(value = "/")
    public ResultVo getCartProduct(@RequestParam("username") String username) {
        return carts.getAll(username);
    }

    @ApiOperation(value  = "edit usr cart", notes = "edit usr cart")
    @PostMapping(value = "/edit")
    public ResultVo editCart(@RequestBody Cart cart){
        return carts.editCart(cart);
    }

}
