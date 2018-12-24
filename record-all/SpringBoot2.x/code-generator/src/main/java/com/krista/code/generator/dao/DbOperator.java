package com.krista.code.generator.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.krista.code.generator.configuration.CodeGeneratorProperty;
import com.krista.code.generator.model.TableModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

/**
 * DbOperator 数据库操作类
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/22 0:11
 */
public class DbOperator {
    private static JdbcTemplate jdbcTemplate;

    public DbOperator() {

    }

    public DbOperator(String url, String userName, String password) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setAsyncInit(false);
        dataSource.init();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void close() {
        ((DruidDataSource) jdbcTemplate.getDataSource()).close();
    }

    /**
     * 获取数据库,去掉系统数据库
     */
    public List<String> databases() {
        String sql = "SHOW DATABASES;";

        List<String> dbList = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
        dbList.removeAll(CodeGeneratorProperty.getSysDbList());

        return dbList;
    }

    public List<TableModel> listTables(String dbName) {
        String sql = "SELECT table_name,table_comment FROM information_schema.tables WHERE table_schema=?";

        return jdbcTemplate.query(sql, new Object[]{dbName}, (rs, rowNum) -> {
            TableModel tableModel = new TableModel();
            tableModel.setTableName(rs.getString("table_name"));
            tableModel.setComment(rs.getString("table_comment"));

            return tableModel;
        });
    }
}
