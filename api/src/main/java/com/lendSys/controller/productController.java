package com.lendSys.controller;


import com.lendSys.dao.ProductMapper;
import com.lendSys.entity.Product;
import com.lendSys.entity.Users;
import com.lendSys.service.goodService;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import com.lendSys.vo.productVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
@Api(value = "Product Management", tags = "Product Management")
@CrossOrigin
public class productController {
    @Resource
    private goodService productService;

    @ApiOperation(value  = "get products", notes = "get products and shown in pages")
    @GetMapping(value = "/pageProduct/{current}/{size}")
    public ResultVo getPageProduct(@PathVariable String current,
                                   @PathVariable String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return productService.getAllGoods(curr, s);
    }

    @ApiOperation(value  = "get product information", notes = "get product information")
    @GetMapping(value = "/{productId}")
    public ResultVo getDetailProduct(@PathVariable String productId) {
        return productService.getDetailGoods(productId);
    }

    @ApiOperation(value  = "add products", notes = "add products")
    @PostMapping(value = "/addProductInfo")
    public ResultVo addProductInfo(@RequestBody Product product) {
        return productService.addGoods(product);
    }
    @ApiOperation(value  = "edit products", notes = "edit products")
    @PostMapping(value = "/editProductInfo")
    public ResultVo editProductInfo(@RequestBody Product product) {
        return productService.editGoods(product);
    }

    @ApiOperation(value  = "delete products", notes = "delete products")
    @DeleteMapping(value = "/delete/{id}")
    public ResultVo deleteProduct(@PathVariable String id) {
        return productService.removeGoods(id);
    }

    @ApiOperation(value  = "search products", notes = "search products")
    @PostMapping(value = "/searchProduct/{current}/{size}")
    public ResultVo searchProduct(@PathVariable String current,
                                        @PathVariable String size,
                                        @RequestBody(required = false) productVo productVo){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return productService.searchGoods(curr,s,productVo);
    }
}
