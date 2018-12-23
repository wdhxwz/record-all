package com.krista.code.generator.configuration;

import com.krista.code.generator.model.DbTypeEnum;

import java.util.*;

/**
 * CodeGeneratorProperty 代码生成的配置
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/21 17:46
 */
public class CodeGeneratorProperty {
    /**
     * 默认数据库
     */
    private static final String DEFAULT_DB = DbTypeEnum.MYSQL.getCode();
    /**
     * 默认大小
     */
    private static final Integer DEFAULT_SIZE = 4;
    /**
     * 数据库与对应的驱动名称映射
     */
    private static final Map<String, String> DB_DRIVER_MAP = new HashMap<>(DEFAULT_SIZE);
    /**
     * 数据表与对应注释映射(从数据库读取)
     */
    private Map<String, String> tableCommentMap = new HashMap<>(DEFAULT_SIZE);
    /**
     * 数据库服务器,可以是域名或ip
     */
    private String dbServer;
    /**
     * 数据库连接端口号
     */
    private String dbPort;
    /**
     * 数据库类型
     *
     * @see DbTypeEnum
     */
    private String dbType;
    /**
     * 数据库连接用户
     */
    private String userName;
    /**
     * 数据库连接密码
     */
    private String password;
    /**
     * 待生成代码的库
     */
    private String dbName;
    /**
     * 待生成代码的表
     */
    private List<String> tables;
    /**
     * 是否生成模型类,默认true
     */
    private boolean isGenerateModel;
    /**
     * 是否生成mapper接口
     */
    private boolean isGenerateMapper;
    /**
     * 是否生成mapper对应的xml文件
     */
    private boolean isGenerateMapperXml;
    /**
     * 是否生成service接口
     */
    private boolean isGenerateService;
    /**
     * 基包路径
     */
    private String basePackage;

    private static List<String> sysDbList = new ArrayList<>();

    static {
        DB_DRIVER_MAP.put(DbTypeEnum.MYSQL.getCode(), com.mysql.jdbc.Driver.class.getName());
        DB_DRIVER_MAP.put(DbTypeEnum.SQL_SERVER.getCode(), "");
        sysDbList.add("information_schema");
        sysDbList.add("mysql");
        sysDbList.add("sys");
    }

    public static String getDefaultDb() {
        return DEFAULT_DB;
    }

    public String getDbUrl() {
        String urlPattern = "jdbc:%s://%s:%s";

        return String.format(urlPattern, this.dbType, this.dbServer, this.dbPort);
    }

    public static List<String> getSysDbList() {
        return sysDbList;
    }

    public CodeGeneratorProperty() {
        isGenerateModel = true;
    }

    public void addTableComment(String tableName, String comment) {
        tableCommentMap.put(tableName, comment);
    }

    public String getDbDriver(String dbType) {
        return DB_DRIVER_MAP.get(dbType);
    }

    public Map<String, String> getTableCommentMap() {
        return tableCommentMap;
    }

    public boolean isGenerateModel() {
        return isGenerateModel;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDbServer() {
        return dbServer;
    }

    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
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

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public boolean isGenerateMapper() {
        return isGenerateMapper;
    }

    public void setGenerateMapper(boolean generateMapper) {
        isGenerateMapper = generateMapper;
    }

    public boolean isGenerateMapperXml() {
        return isGenerateMapperXml;
    }

    public void setGenerateMapperXml(boolean generateMapperXml) {
        isGenerateMapperXml = generateMapperXml;
    }

    public boolean isGenerateService() {
        return isGenerateService;
    }

    public void setGenerateService(boolean generateService) {
        isGenerateService = generateService;
    }
}
