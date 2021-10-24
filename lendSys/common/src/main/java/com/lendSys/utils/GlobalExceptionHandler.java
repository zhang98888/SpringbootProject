package com.lendSys.utils;

import com.lendSys.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;


// handle exception globally . Once there is exception, it will return
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //The exception it will handle
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return new ResultVo(1001,"There is system error",0,null);
    }

    // special exception
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseBody
    public ResultVo error(FileNotFoundException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return new ResultVo(1001,"There is system error",0,null);
    }
}
