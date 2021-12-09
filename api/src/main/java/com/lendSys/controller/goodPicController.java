package com.lendSys.controller;


import com.lendSys.entity.Category;
import com.lendSys.entity.ProductPicture;
import com.lendSys.service.goodPicService;
import com.lendSys.service.goodService;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/goodPic")
@CrossOrigin
@Api(value = "Good Picture Upload", tags = "Good/Category Picture")
public class goodPicController {
    @Resource
    private goodPicService picService;

    @ApiOperation(value = "get pictures of goods", notes = "get all good pictures and shown by pages")
    @GetMapping(value = "/getAllGoodPic/{current}/{size}")

    public ResultVo getAllGoodPic(@PathVariable @ApiParam(name = "current",value = "current page number") String current,
                                  @PathVariable @ApiParam(name = "size",value = "current page size") String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return picService.getGoodPic(curr, s);
    }

    @ApiOperation(value = "get pictures of goods", notes = "get all good pictures and shown by pages")
    @GetMapping(value = "/getProductPic/{current}/{size}")
    public ResultVo getProductPic(@PathVariable String current,
                                  @PathVariable String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return picService.getProductPic(curr, s);
    }

    @ApiOperation(value = "add pictures for resources", notes = "add pictures for resources")
    @PostMapping(value = "/addGoodPic")
    public ResultVo addGoodPic(@RequestBody ProductPicture productPicture) {
        return picService.addGoodPic(productPicture);
    }

    @ApiOperation(value = "edit pictures for resources", notes = "edit pictures for resources")
    @PostMapping(value = "/editGoodPic")
    public ResultVo editGoodPic(@RequestBody ProductPicture productPicture) {
        return picService.editGoodPic(productPicture);
    }

    @ApiOperation(value = "delete pictures of resources", notes = "delete pictures of resources")
    @DeleteMapping(value = "/deleteGoodPic/{id}")
    public ResultVo deleteGoodPic(@PathVariable String id) {
        return picService.removeGoodPic(id);
    }

    @ApiOperation(value = "search pictures", notes = "search pictures of resources")
    @PostMapping(value = "/searchGoodPic/{current}/{size}")
    public ResultVo searchGoodPic(@PathVariable String current,
                                  @PathVariable String size,
                                  @RequestBody HashMap<String, String> map) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        int pictureId = Integer.parseInt(map.get("pictureId"));
        return picService.searchGoodPic(curr, s, pictureId);
    }

    @ApiOperation(value = "get shown pictures", notes = "get the pictures will shown on the main pages")
    @GetMapping(value = "/getAdvanceGoodPic/{num}")
    public ResultVo getAdvanceGoodPic(@PathVariable String num) {
        int numId = Integer.parseInt(num);
        return picService.getAdvanceGoodPic(numId);
    }
    @ApiOperation(value = "get pictures of category", notes = "get pictures of category and shown by pages")
    @PostMapping(value = "/category/{categoryId}/{current}/{size}")
    public ResultVo gatCategoryPic(@PathVariable String categoryId, @PathVariable String current,
                                   @PathVariable String size) {

        return picService.gatCategoryPic(categoryId, current, size);
    }


}
