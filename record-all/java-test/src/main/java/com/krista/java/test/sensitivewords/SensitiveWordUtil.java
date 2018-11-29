package com.krista.java.test.sensitivewords;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * SensitiveWordUtil 敏感词工具类
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 16:01
 */
public class SensitiveWordUtil {
    /**
     * 敏感词库
     */
    public static Map sensitiveWordMap = null;

    /**
     * 敏感词库敏感词数量
     */
    public static int getWordSize() {
        return sensitiveWordMap == null ? 0 : sensitiveWordMap.size();
    }

    /**
     * 是否包含敏感词
     */
    public static boolean isContainSensitiveWord(String txt, MatchTypeEnum matchType) {
        for (int index = 0; index < txt.length(); index++) {
            int matchFlag = checkSensitiveWord(txt, index, matchType);
            if (matchFlag > 0) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> getSensitiveWord(String txt, MatchTypeEnum matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();

        for (int index = 0; index < txt.length(); index++) {
            int length = checkSensitiveWord(txt, index, matchType);
            if (length > 0) {
                // 将检测出的敏感词保存到集合中
                sensitiveWordList.add(txt.substring(index, index + length));
                index = index + length - 1;
            }
        }

        return sensitiveWordList;
    }


    /**
     * 替换敏感词
     *
     * @param txt
     * @param matchType
     * @param replaceChar
     * @return
     */
    public static String replaceSensitiveWord(String txt, MatchTypeEnum matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 替换敏感词内容
     *
     * @param replaceChar
     * @param length
     * @return
     */
    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查敏感词数量
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return
     */
    public static int checkSensitiveWord(String txt, int beginIndex, MatchTypeEnum matchType) {
        boolean flag = false;

        // 记录敏感词数量
        int matchFlag = 0;
        char word = 0;
        Map nowMap = SensitiveWordUtil.sensitiveWordMap;
        for (int index = beginIndex; index < txt.length(); index++) {
            word = txt.charAt(index);

            // 判断该字是否存在于敏感词库中
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag++;
                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                if ("1".equals(nowMap.get(SensitiveWordInit.IS_END_KEY) + "")) {
                    flag = true;
                    // 判断过滤类型，如果是小过滤则跳出循环，否则继续循环
                    if (MatchTypeEnum.minMatchType == matchType) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
}
