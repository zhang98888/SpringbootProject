package com.lendSys.controller;


import com.lendSys.dao.ProductMapper;
import com.lendSys.entity.Product;
import com.lendSys.entity.Users;
import com.lendSys.service.goodService;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import com.lendSys.vo.productVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
@Api(value = "Product Management", tags = "Product Management")
@CrossOrigin
public class productController {
    @Resource
    private goodService productService;

    @GetMapping(value = "/pageProduct/{current}/{size}")
    public ResultVo getPageProduct(@PathVariable String current,
                                   @PathVariable String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return productService.getAllGoods(curr, s);
    }

    @GetMapping(value = "/{productId}")
    public ResultVo getDetailProduct(@PathVariable String productId) {
        return productService.getDetailGoods(productId);
    }

    @PostMapping(value = "/addProductInfo")
    public ResultVo addProductInfo(@RequestBody Product product) {
        return productService.addGoods(product);
    }

    @PostMapping(value = "/editProductInfo")
    public ResultVo editProductInfo(@RequestBody Product product) {
        return productService.editGoods(product);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResultVo deleteProduct(@PathVariable String id) {
        return productService.removeGoods(id);
    }

    @PostMapping(value = "/searchProduct/{current}/{size}")
    public ResultVo getPageUsersAdvance(@PathVariable String current,
                                        @PathVariable String size,
                                        @RequestBody(required = false) productVo productVo){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return productService.searchGoods(curr,s,productVo);
    }
}
