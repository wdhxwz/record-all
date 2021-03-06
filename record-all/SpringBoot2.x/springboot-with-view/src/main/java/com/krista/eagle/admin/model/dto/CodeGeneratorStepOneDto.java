package com.krista.eagle.admin.model.dto;

import javax.validation.constraints.NotNull;

/**
 * CodeGeneratorStepOneDto
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/23 12:23
 */
public class CodeGeneratorStepOneDto {
    @NotNull
    private String dbType;
    @NotNull
    private String host;
    @NotNull
    private String port;
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
