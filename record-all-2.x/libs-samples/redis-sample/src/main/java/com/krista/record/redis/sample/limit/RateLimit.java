package com.krista.record.redis.sample.limit;

/**
 * RateLimit
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 14:43
 */
public interface RateLimit {
    /**
     * isActionAllowed 是否允许操作
     *
     * @param field     字段值,可以是userId/IP等需要限定的用户标识
     * @param actionKey 操作类型，如注册/发短信等
     * @param period    周期,单位是秒
     * @param maxCount  周期内的最大次数
     * @return boolean 操作小于等于最大次数时，返回true，反正返回false
     * @author krista
     * @date 2019/4/7 14:48
     */
    boolean isActionAllowed(String field, String actionKey, int period, int maxCount);
}
