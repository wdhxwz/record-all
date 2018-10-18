package com.krista.springboot.v1x.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
