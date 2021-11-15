package com.lendSys.controller;

import com.lendSys.entity.Cart;
import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.service.cartService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
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
    @GetMapping(value = "/{current}/{size}")
    public ResultVo getCartProduct(@PathVariable String current,
                                   @PathVariable String size,
                                   @RequestParam("username") String username) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return carts.getAllCart(curr,s,username);
    }

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

    @PostMapping(value = "/remove")
    public ResultVo removeCart(@RequestBody List<String> ids){
        return carts.removeCart(ids);
    }

}
