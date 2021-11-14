package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "cart information",
        description = "All product in cart")
public class CartVo {
    @ApiModelProperty(value = "cart id")
    private Integer cartId;

    @ApiModelProperty(value = "product id")
    private Integer productid;

    @ApiModelProperty(value = "user id")
    private Integer userId;

    @ApiModelProperty(value = "cart number")
    private Integer cartNum;

    @ApiModelProperty(value = "cart time")
    private Date cartTime;

    @ApiModelProperty(value = "product Name")
    private String productName;
}
