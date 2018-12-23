package com.krista.code.generator.model;

/**
 * TableModel 从数据库查出的表模型(表名 + 表注释)
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/22 0:04
 */
public class TableModel {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String comment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}