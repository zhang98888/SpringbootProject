package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Picture Information Object",
        description = "Show picture details information")
public class PicInfoVo {

    @ApiModelProperty(value = "Product Name")
    private String productname;
    @ApiModelProperty(value = "rent number")
    private int rent;
    @ApiModelProperty(value = "url")
    private String url;
}
