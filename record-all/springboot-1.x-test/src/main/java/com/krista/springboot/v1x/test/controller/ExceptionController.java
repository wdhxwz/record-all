package com.krista.springboot.v1x.test.controller;

import com.krista.extend.base.exception.CodeException;
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
    public String index() {
        throw new RuntimeException("手动抛异常");
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public String error() {
        // throw new NoClassDefFoundError("手动抛异常");
        throw new CodeException("手动抛异常", 300);
    }

    @ExceptionHandler(CodeException.class)
    @ResponseBody
    public String handleException() {
        return "抛异常啦";
    }
}
