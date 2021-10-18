package com.lendSys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {

    // The code will response to the front-end
    private int code;
    // The msg will response to the front-end
    private String msg;
    // The data will response to the front-end
    private Object data;

}
