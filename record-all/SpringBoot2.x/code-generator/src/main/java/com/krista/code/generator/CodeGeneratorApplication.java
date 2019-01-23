package com.krista.code.generator;

import com.google.common.base.CaseFormat;
import com.krista.code.generator.configuration.KristaCommentGenerator;
import com.krista.extend.utils.JsonUtil;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String MODULE_PATH = "SpringBoot2.x/code-generator";
    private static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator + MODULE_PATH;
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/template";

    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, IOException {
        // test();

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

        for (String tableName : Constants.tables) {
            if (StringUtils.isNotEmpty(tableName)) {
                mybatisGenerator(tableName);
                genService(tableName, "");
            }
        }
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

    private static void mybatisGenerator(String tableName) throws InvalidConfigurationException, InterruptedException, SQLException, IOException {
        Context context = getContext(Constants.contextId);

        // 2. 添加插件配置:用于扩展或修改通过MBG代码生成器生成的代码(一般不需要配置)
        // 用于集成通用mapper
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType(MapperPlugin.class.getName());
        pluginConfiguration.addProperty("mappers", Constants.myMapper);
        context.addPluginConfiguration(pluginConfiguration);

        // 3. 设置注释生成器：具体就是生成表或字段的备注信息
        CommentGeneratorConfiguration commentGeneratorConfiguration = getCommentGeneratorConfiguration();
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        // 4. 设置数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(Constants.driverClass);
        jdbcConnectionConfiguration.setConnectionURL(Constants.connectUrl);
        jdbcConnectionConfiguration.setPassword(Constants.dbPassword);
        jdbcConnectionConfiguration.setUserId(Constants.dbUser);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // 5. 设置JDBC Type 与Java Type之间的映射解析器
        /** context.setJavaTypeResolverConfiguration(null);**/

        // 6. 生成实体类地址配置
        // 该元素只有两个属性， 都是必选的
        // targetPackage:生成实体类存放的包名， 一般就是放在该包下
        // targetProject:指定目标项目路径， 使用的是文件系统的绝对路径
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(Constants.basePackage + Constants.flag + Constants.modelPackage);
        String dir = Constants.saveDir;
        File file = new File(dir);
        file.mkdir();
        javaModelGeneratorConfiguration.setTargetProject(dir);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // 7. map.xml相关配置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage(Constants.basePackage + Constants.flag + Constants.xmlPackage);
        sqlMapGeneratorConfiguration.setTargetProject(dir);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // 8. 生成Mapper接口相关配置
        // MyBatis3Simple:
        //  ANNOTATEDMAPPER:基于注解的Mapper接口， 不会有对应的XML映射文件
        //  XMLMAPPER:所有的方法都在XML中， 接口调用依赖XML文件。
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage(Constants.basePackage + Constants.flag + Constants.mapperPackage);
        javaClientGeneratorConfiguration.setTargetProject(dir);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // 表配置
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
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

    private static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", Constants.date);
            data.put("author", Constants.author);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", Constants.basePackage);

            File file = new File(Constants.saveDir + File.separator +
                    packageConvertPath(Constants.basePackage + Constants.flag + Constants.servicePackage) +
                    File.separator + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            LOGGER.info(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(Constants.saveDir + File.separator +
                    packageConvertPath(Constants.basePackage + Constants.flag + Constants.serviceImplPackage)
                    + File.separator + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            LOGGER.info(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
