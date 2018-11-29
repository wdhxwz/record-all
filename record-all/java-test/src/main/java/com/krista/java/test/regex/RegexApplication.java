package com.krista.java.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexApplication 正则测试
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/27 9:41
 */
public class RegexApplication {
    /**
     * 标点匹配正则
     * https://www.cnblogs.com/qixuejia/p/4211428.html
     * https://www.cnblogs.com/MarsJiang/p/springMVC_annotations.html
     */
    private static final Pattern pattern = Pattern.compile("\\p{P}");

    public static void main(String[] args) {
        String nickName = "人帅,刀才快";
        Matcher matcher = pattern.matcher(nickName);
        System.out.println(matcher.find());
        System.out.println(matcher.replaceAll(""));
    }
}
