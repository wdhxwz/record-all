package com.krista.springboot.v1x.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 用户
 *
 * @author dw_wangdonghong
 * @date 2018/10/25 19:19
 */
@ApiModel(value = "用户")
@Entity
public class User {
    @ApiModelProperty(value = "用户id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户登录名")
    @Column
    private String username;
    @ApiModelProperty(value = "用户姓名")
    @Column
    private String name;
    @ApiModelProperty(value = "用户年龄")
    @Column
    private Integer age;
    @ApiModelProperty(value = "账户余额")
    @Column
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
