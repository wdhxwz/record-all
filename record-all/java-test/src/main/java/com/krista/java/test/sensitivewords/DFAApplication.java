package com.krista.java.test.sensitivewords;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DFAApplication DFA:Deterministic Finite Automaton，也就是确定有穷自动机
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 15:29
 */
public class DFAApplication {
    private static String PATH = "/sensitiveWord.txt";
    private static String ENCODING = "UTF-8";

    public static void main(String[] args) {
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        Long start = System.currentTimeMillis();

        Map map = sensitiveWordInit.init(getSensitiveWords());

        System.out.println("finish sensitiveWordInit,耗时(ms):" + (System.currentTimeMillis() - start));

        SensitiveWordUtil.sensitiveWordMap = map;

        String text = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法.轮.功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三.级.片 深人静的晚上，关上电话静静的发呆着。";

        start = System.currentTimeMillis();
        Set<String> set = SensitiveWordUtil.getSensitiveWord(text, MatchTypeEnum.maxMatchType);
        System.out.println("finish 检测,耗时(ms):" + (System.currentTimeMillis() - start) + ",敏感词个数：" + set.size());
        set.forEach(s -> {
            System.out.println(s);
        });

        start = System.currentTimeMillis();
        String txt = SensitiveWordUtil.replaceSensitiveWord(text, MatchTypeEnum.maxMatchType, "*");
        System.out.println("finish 敏感词替换,耗时(ms):" + (System.currentTimeMillis() - start));
        System.out.println("替换后：" + txt);
    }

    private static List<SensitiveWord> getSensitiveWords() {
        List<SensitiveWord> sensitiveWords = new ArrayList<>();
        sensitiveWords.add(new SensitiveWord("自杀"));
        sensitiveWords.add(new SensitiveWord("三.级.片"));
        sensitiveWords.add(new SensitiveWord("法.轮.功"));
        sensitiveWords.add(new SensitiveWord("中国人"));
        sensitiveWords.add(new SensitiveWord("中国人民"));
        sensitiveWords.add(new SensitiveWord("你妈"));
        sensitiveWords.add(new SensitiveWord("傻逼"));
        sensitiveWords.add(new SensitiveWord("智障"));

        try (InputStreamReader read = new InputStreamReader(DFAApplication.class.getResourceAsStream(PATH), ENCODING);) {
            @SuppressWarnings("resource")
            BufferedReader bufferedReader = new BufferedReader(read);
            String txt = null;
            while ((txt = bufferedReader.readLine()) != null) {
                sensitiveWords.add(new SensitiveWord(txt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sensitiveWords;
    }
}
