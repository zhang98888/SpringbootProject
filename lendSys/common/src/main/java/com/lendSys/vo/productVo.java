package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "productVo Object",
        description = "Package the data interface of product")
public class productVo {

    @ApiModelProperty(value = "product id")
    private String productId;
    @ApiModelProperty(value = "product name")
    private String productName;
}
