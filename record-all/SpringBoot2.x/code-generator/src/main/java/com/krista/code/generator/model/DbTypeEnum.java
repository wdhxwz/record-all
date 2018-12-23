package com.krista.code.generator.model;

import com.krista.extend.base.BaseEnum;

/**
 * DbTypeEnum
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/21 23:48
 */
public enum DbTypeEnum implements BaseEnum<String> {
    /**
     * mysql数据库
     */
    MYSQL("mysql", "mysql数据库"),
    /**
     * sqlServer数据库
     */
    SQL_SERVER("sqlServer", "sqlServer数据库");

    DbTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
