package com.krista.code.generator;

import com.krista.code.generator.configuration.CodeGeneratorProperty;
import com.krista.code.generator.configuration.KristaCommentGenerator;
import com.krista.code.generator.dao.DbOperator;
import com.krista.code.generator.model.TableModel;
import com.krista.extend.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.List;

/**
 * CodeGeneratorApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/21 10:30
 */
public class CodeGeneratorApplication {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorApplication.class);

    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, IOException {
        // test();
        mybatisGenerator();
//        String url = "jdbc:mysql://mysql.krista.com:3306";
//        String userName = "root";
//        String password = "1q2w#E$R";
//
//        // 返回db给界面下拉
//        DbOperator dbOperator = new DbOperator(url, userName, password);
//        List<String> dbList = dbOperator.databases();
//        LOGGER.info(JsonUtil.toJson(dbList));
//
//        // 返回指定db的所有表给界面选择
//        String dbName = "adminCenter";
//        List<TableModel> tableModels = dbOperator.listTables(dbName);
//        LOGGER.info(JsonUtil.toJson(tableModels));
//
//        CodeGeneratorProperty codeGeneratorProperty = new CodeGeneratorProperty();
//        tableModels.forEach(tableModel -> {
//            if (StringUtils.isNotEmpty(tableModel.getComment())) {
//                codeGeneratorProperty.addTableComment(tableModel.getTableName(), tableModel.getComment());
//            }
//        });
    }

    private static void test() {
        // URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try (Connection con = DriverManager.getConnection(url, user, password);) {
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            // 2.创建statement类对象，用来执行SQL语句！！
            try (Statement statement = con.createStatement();) {
                // 要执行的SQL语句
                String sql = "SHOW DATABASES;";
                sql = "SELECT table_name FROM information_schema.tables WHERE table_schema='pushsystem';";
                sql = "SELECT column_name,ordinal_position,column_default,data_type,column_comment FROM information_schema.columns WHERE table_schema ='pushsystem'  AND table_name = 'push_app';";
                // 3.ResultSet类，用来存放获取的结果集！！
                try (ResultSet rs = statement.executeQuery(sql);) {
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
    }

    private static void mybatisGenerator() throws InvalidConfigurationException, InterruptedException, SQLException, IOException {
        Context context = getContext("test");

        // 2. 添加插件配置:用于扩展或修改通过MBG代码生成器生成的代码(一般不需要配置)
        /** context.addPluginConfiguration(null);**/

        // 3. 设置注释生成器：具体就是生成表或字段的备注信息
        CommentGeneratorConfiguration commentGeneratorConfiguration = getCommentGeneratorConfiguration();
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        // 4. 设置数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/pushsystem");
        jdbcConnectionConfiguration.setPassword("123456");
        jdbcConnectionConfiguration.setUserId("root");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // 5. 设置JDBC Type 与Java Type之间的映射解析器
        /** context.setJavaTypeResolverConfiguration(null);**/

        // 6. 生成实体类地址配置
        // 该元素只有两个属性， 都是必选的
        // targetPackage:生成实体类存放的包名， 一般就是放在该包下
        // targetProject:指定目标项目路径， 使用的是文件系统的绝对路径
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage("com.krista.test.model");
        String dir = "C:\\Users\\Administrator\\Desktop\\test\\" + System.currentTimeMillis();
        File file = new File(dir);
        file.mkdir();
        javaModelGeneratorConfiguration.setTargetProject(dir);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // 7. map.xml相关配置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage("com.krista.test.xml");
        sqlMapGeneratorConfiguration.setTargetProject(dir);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // 8. 生成Mapper接口相关配置
        // MyBatis3Simple:
        //  ANNOTATEDMAPPER:基于注解的Mapper接口， 不会有对应的XML映射文件
        //  XMLMAPPER:所有的方法都在XML中， 接口调用依赖XML文件。
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage("com.krista.test.dao");
        javaClientGeneratorConfiguration.setTargetProject(dir);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // 表配置
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("push_app");
        context.addTableConfiguration(tableConfiguration);

        // 相关配置
        Configuration configuration = new Configuration();
        configuration.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(true);

        // 存放执行过程中产生的warning
        List<String> warnings = Collections.emptyList();

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println(JsonUtil.toJson(warnings));
    }

    private static Context getContext(String contextId) {
        // ModelType : 定义如何生成实体类
        // ModelType.FLAT:每张表生成一个实体类
        Context context = new Context(ModelType.FLAT);
        context.setId(contextId);

        // 指定生成代码的运行环境
        // 支持：MyBatis3(默认值)、MyBatis3Simple、Ibatis2Java2、Ibatis2Java5
        context.setTargetRuntime("MyBatis3Simple");

        // 1. 添加属性，property支持的属性有
        // autoDelimitKeywords ==> 当表名或者字段名为SQL关键字的时候，设置该属性为true， MBG会自动给表名或字段名添加分隔符
        // beginningDelimiter ==> 开始分隔符，默认值为双引号，Mysql中要改为反单引号(`)
        // endingDelimiter ==> 结束分隔符，默认值为双引号，Mysql中要改为反单引号(`)
        // javaFileEncoding ==> 设置要使用的Java文件的编码， 默认使用当前平台的编码
        // javaFormatter ==> 格式化java代码
        // xmlFormatter ==>  格式化xml代码
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");

        return context;
    }

    private static CommentGeneratorConfiguration getCommentGeneratorConfiguration() {
        // 3. 设置注释生成器：具体就是生成表或字段的备注信息
        // org.mybatis.generator.api.CommentGenerator
        // 有两个可配置属性：
        // suppressAllComments:阻止生成注释， 默认为false
        // suppressDate:阻止生成的注释包含时间戳， 默认为false
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.addProperty("suppressDate", "true");
        commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
        // 注释生成类
        commentGeneratorConfiguration.setConfigurationType(KristaCommentGenerator.class.getName());

        return commentGeneratorConfiguration;
    }
}
