package com.krista.java.test.sensitivewords;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SensitiveWordInit 敏感词初始化
 * 敏感词树的深度取决于长度最长的敏感词
 * 敏感词树第一层的广度取决于第一个词不一致的敏感词个数
 * 如：中国人、中国人民、五星红旗、习大大
 * 由上面的词库组成的树第一层有3个键,最大深度为4
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 15:02
 */
public class SensitiveWordInit {
    public static final String IS_END_KEY = "isEnd";

    /**
     * 敏感词库
     */
    public HashMap sensitiveWordMap;

    /**
     * init 初始化敏感词成Map
     *
     * @param sensitiveWords 敏感词列表
     * @return java.util.Map
     * @author dw_wangdonghong
     * @date 2018/11/29 15:04
     */
    public Map init(List<SensitiveWord> sensitiveWords) {
        Set<String> sensitiveWordSet = sensitiveWords.stream()
                .map(SensitiveWord::getName)
                .collect(Collectors.toSet());

        addSensitiveWordToHashMap(sensitiveWordSet);

        return sensitiveWordMap;
    }

    private void addSensitiveWordToHashMap(Set<String> sensitiveWordSet) {
        sensitiveWordMap = new HashMap(sensitiveWordSet.size());

        // 用来按照相应的格式保存敏感词库数据
        Map nowMap = null;

        // 用来辅助构建敏感词库
        Map<String, String> newWordMap = null;

        // 遍历敏感词集合
        for (String sensitiveWord : sensitiveWordSet) {
            nowMap = sensitiveWordMap;

            // 遍历敏感词的字符
            for (int index = 0; index < sensitiveWord.length(); index++) {
                char keyChar = sensitiveWord.charAt(index);

                // 判断该字符是否已经在敏感词树中
                // 如果keyChar不是在树的第一层呢，使用nowMap相当于递归
                Object word = nowMap.get(keyChar);
                if (word != null) {
                    nowMap = (Map) word;
                } else {
                    newWordMap = new HashMap<>();
                    newWordMap.put(IS_END_KEY, "0");
                    nowMap.put(keyChar, newWordMap);
                    nowMap = newWordMap;
                }

                // 如果该字是当前敏感词的最后一个字，则标识为结尾字
                if (index == sensitiveWord.length() - 1) {
                    nowMap.put(IS_END_KEY, 1);
                }
            }
        }
    }
}
