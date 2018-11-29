package com.krista.java.test.sensitivewords;

/**
 * MatchTypeEnum
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 16:16
 */
public enum MatchTypeEnum {
    /**
     * 只过滤最小敏感词(匹配到一个即结束)
     */
    minMatchType(1, "只过滤最小敏感词(匹配到一个即结束)"),
    /**
     * 过滤所有敏感词
     */
    maxMatchType(2, "过滤所有敏感词");

    MatchTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }
}
