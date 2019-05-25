package com.krista.code.generator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Constants
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/23 9:46
 */
public class Constants {
    /**
     * 上下文相关配置
     */
    static String contextId = "test";


    /**
     * 数据库相关配置
     */
    static String driverClass = "com.mysql.jdbc.Driver";
    static String connectUrl = "jdbc:mysql://mysql.krista.com:3306/wetech_admin";
    static String dbPassword = "1q2w#E$R";
    static String dbUser = "root";
//    public static List<String> tables = Arrays.asList(
//            "c_file", "c_file_relation", "d_doctor", "d_doctor_patient",
//            "p_assay_list", "p_diet_log", "p_evaluation", "p_food_prefer",
//            "p_medical_history", "p_nutrition_report", "p_nutrition_service", "p_patient"
//    );

    public static List<String> tables = Arrays.asList("c_user_log");

    /**
     * 程序相关配置
     */
    static String myMapper = "com.krista.admin.utils.MyMapper";
    static String basePackage = "com.krista.admin";
    static String modelPackage = "model";
    static String mapperPackage = "dao";
    static String xmlPackage = "xml";
    static String servicePackage = "service";
    static String serviceImplPackage = "service.impl";
    static String flag = ".";
    static String saveDir = "C:\\Users\\wdhcxx\\Desktop\\test\\" + System.currentTimeMillis();
    static String author = "krista";
    static String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

}
