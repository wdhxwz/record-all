package com.krista.springboot.v1x.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: dw_wanghonghong
 * @Date: 2018/10/16 10:15
 * @Description: 异常处理
 */
@Controller
@RequestMapping(value = "/exception")
public class ExceptionController {
    @RequestMapping(value = "/")
    public String index(){
        throw new RuntimeException("手动抛异常");
    }

    @RequestMapping(value = "/error")
    public String error(){
        // throw new NoClassDefFoundError("手动抛异常");
        throw new Error("手动抛异常");
    }

    @ExceptionHandler(Error.class)
    @ResponseBody
    public String handleException() {
        return "抛异常啦";
    }
}
