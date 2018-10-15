package com.krista.springboot.v1x.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: dw_wanghonghong
 * @Date: 2018/10/15 14:28
 * @Description:
 */
@RequestMapping
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    @ResponseBody
    public String index(){
        return "Hello Spring Boot 233";
    }
}
