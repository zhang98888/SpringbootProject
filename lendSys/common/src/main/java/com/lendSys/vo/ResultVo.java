package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultVo Object",
        description = "Package the data interface reply to the front end")
public class ResultVo {

    // The code will response to the front-end
    @ApiModelProperty(value = "Reply code")
    private int code;
    // The msg will response to the front-end
    @ApiModelProperty(value = "Reply message")
    private String msg;
    // The data will response to the front-end
    @ApiModelProperty(value = "Reply data")
    private Object data;

}
