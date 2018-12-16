package com.krista.eagle.admin.model.dto;

import java.io.Serializable;

/**
 * LoginDto
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/15 14:05
 */
public class LoginDto implements Serializable {
    /**
     * 登录名
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 记住
     */
    private Boolean rememberMe;

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
