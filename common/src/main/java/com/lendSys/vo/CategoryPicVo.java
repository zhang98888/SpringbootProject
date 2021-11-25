package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "product by category",
        description = "All product with different category")
public class CategoryPicVo {
    @ApiModelProperty(value = "picture id")
    private int pictureId;
    @ApiModelProperty(value = "product id")
    private int productId;
    @ApiModelProperty(value = "product name")
    private String productName;
    @ApiModelProperty(value = "category id")
    private int categoryId;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "rent num")
    private int rentNum;
}
