package com.krista.java.test.sensitivewords;

import java.util.HashSet;
import java.util.Set;

/**
 * SimpleApplication:遍历敏感词库,对文本进行检测
 * 优点：简单易实现
 * 缺点：敏感词库很大,文本很长时，效率很差
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 14:44
 */
public class SimpleApplication {
    private static Set<String> sensitiveWords = new HashSet<>();

    public static void main(String[] args) {
        initSensitiveWords();
        String text = "shit,你妈是傻逼啊";
        sensitiveWordCheck(text);
    }

    private static void initSensitiveWords() {
        sensitiveWords.add("shit");
        sensitiveWords.add("傻逼");
        sensitiveWords.add("你妈");
    }

    private static void sensitiveWordCheck(String sourceText) {
        sensitiveWords.forEach(sensitiveWord -> {
            if (sourceText.contains(sensitiveWord)) {
                System.out.println("输入的文本存在敏感词:" + sensitiveWord);
            }
        });
    }
}
