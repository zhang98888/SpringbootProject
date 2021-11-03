package com.lendSys.controller;

import com.lendSys.entity.Category;
import com.lendSys.service.categoryService;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
@Api(value = "Category Management", tags = "Category Management")
@CrossOrigin
public class CategoryController {
    @Resource
    private categoryService cateService;

    @GetMapping(value = "/getAllCategory/{current}/{size}")
    public ResultVo getAllCategory(@PathVariable String current,
                                   @PathVariable String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return cateService.getAllCategory(curr, s);
    }

    @PostMapping(value = "/addCategoryInfo")
    public ResultVo addCategoryInfo(@RequestBody Category category) {
        return cateService.addCategory(category);
    }

    @PostMapping(value = "/editCategoryInfo")
    public ResultVo editCategoryInfo(@RequestBody Category category) {
        return cateService.editCategory(category);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResultVo deleteProduct(@PathVariable String id) {
        return cateService.removeCategory(id);
    }

    @PostMapping(value = "/searchCategory/{current}/{size}")
    public ResultVo searchCategory(@PathVariable String current,
                                        @PathVariable String size,
                                        @RequestBody(required = false) CategoryVo categoryVo){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return cateService.searchCategory(curr,s,categoryVo);
    }
}
