package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CategoryVo Object",
        description = "Package the data interface of category")
public class CategoryVo {

    @ApiModelProperty(value = "category id")
    private String categoryId;
    @ApiModelProperty(value = "category name")
    private String categoryName;
}


