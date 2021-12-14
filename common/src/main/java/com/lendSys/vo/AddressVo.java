package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
/*
it is used for get users' address.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AddressVo",
        description = "Used to transfer data")
public class AddressVo {

    @ApiModelProperty("userid")
    private Integer userid;

    @ApiModelProperty("receiver_name")
    private String receiverName;

    @ApiModelProperty("receiver_mobile")
    private String receiverMobile;

    @ApiModelProperty("city")
    private String city;

    @ApiModelProperty("address")
    private String address;

    @ApiModelProperty("postcode")
    private String postcode;

    @ApiModelProperty("default_address")
    private String defaultAddress;
}
