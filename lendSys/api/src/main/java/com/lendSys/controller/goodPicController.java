package com.lendSys.controller;


import com.lendSys.entity.Category;
import com.lendSys.entity.ProductPicture;
import com.lendSys.service.goodPicService;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/goodPic")
@CrossOrigin
@Api(value = "Good Picture Upload",tags = "Good Picture Upload")
public class goodPicController {
    @Resource
    private goodPicService picService;

    @GetMapping(value = "/getAllGoodPic/{current}/{size}")
    public ResultVo getAllGoodPic(@PathVariable String current,
                                   @PathVariable String size) {
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return picService.getGoodPic(curr, s);
    }

    @PostMapping(value = "/addGoodPic")
    public ResultVo addGoodPic(@RequestBody ProductPicture productPicture) {
        return picService.addGoodPic(productPicture);
    }

    @PostMapping(value = "/editGoodPic")
    public ResultVo editGoodPic(@RequestBody ProductPicture productPicture) {
        return picService.editGoodPic(productPicture);
    }

    @DeleteMapping(value = "/deleteGoodPic/{id}")
    public ResultVo deleteGoodPic(@PathVariable String id) {
        return picService.removeGoodPic(id);
    }

    @PostMapping(value = "/searchGoodPic/{current}/{size}")
    public ResultVo searchGoodPic(@PathVariable String current,
                                  @PathVariable String size,
                                  @RequestBody HashMap<String,String> map){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        int pictureId = Integer.parseInt(map.get("pictureId"));
        return picService.searchGoodPic(curr,s,pictureId);
    }

}
