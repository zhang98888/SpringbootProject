package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "cart and orderVo",
        description = "Used to transfer data")
public class cartsOrderVo {

    @ApiModelProperty("carts list")
    private CartVo[] carts;

    @ApiModelProperty(value = "borrowlength")
    private Integer borrowlength;

    @ApiModelProperty(value = "orderRemark")
    private Integer orderRemark;

}
