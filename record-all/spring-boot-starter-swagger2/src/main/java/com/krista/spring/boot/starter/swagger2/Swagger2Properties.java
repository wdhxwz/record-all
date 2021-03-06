package com.krista.spring.boot.starter.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "swagger2")
@Component
public class Swagger2Properties {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用描述
     */
    private String description;
    /**
     * 应用版本号
     */
    private String version;
    /**
     * 开发用户
     */
    private String userName;
    /**
     * 用户url
     */
    private String url;
    /**
     * 用户邮箱
     */
    private String email;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
