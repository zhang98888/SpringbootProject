package com.lendSys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserVo Object",
        description = "Package the data interface reply to the front end")
public class UserVo {
    // The code will response to the front-end
    @ApiModelProperty(value = "user id")
    private String userid;
    // The msg will response to the front-end
    @ApiModelProperty(value = "user name")
    private String username;
    @ApiModelProperty(value = "user level")
    private String userlevel;
}
