package com.krista.springframework.v4x.ioc.test.annotation;

/**
 * Director
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 17:40
 */
public class Director {
    private String name = "krista";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("hello from director 2");
    }
}
