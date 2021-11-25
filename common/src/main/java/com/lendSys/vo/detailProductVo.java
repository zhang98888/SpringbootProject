package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "detailProductVo Object",
        description = "one product with picture and other information")
public class detailProductVo {
    @ApiModelProperty(value = "product id")
    private int productId;
    @ApiModelProperty(value = "product name")
    private String productName;
    @ApiModelProperty(value = "rent number")
    private int rentNum;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "picture id")
    private int pictureId;
    @ApiModelProperty(value = "product level")
    private String productLevel;
    @ApiModelProperty(value = "product des")
    private String productDes;
    @ApiModelProperty(value = "product status")
    private int productStatus;
}
