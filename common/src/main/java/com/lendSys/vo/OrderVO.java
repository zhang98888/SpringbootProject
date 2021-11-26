package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderVo Object",
        description = "Package the data interface reply to the front end")
public class OrderVO {
    @ApiModelProperty(value = "startDate")
    private Date startDate;

    @ApiModelProperty(value = "endDate")
    private Date endDate;


    @ApiModelProperty(value = "borrowlength")
    private Integer borrowlength;

    @ApiModelProperty(value = "orderStatus")
    private Integer orderStatus;

    @ApiModelProperty(value = "orderRemark")
    private Integer orderRemark;
}
