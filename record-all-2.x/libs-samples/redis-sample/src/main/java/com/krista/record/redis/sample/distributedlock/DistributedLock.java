package com.krista.record.redis.sample.distributedlock;

/**
 * DistributedLock 分布式锁
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 15:49
 */
public interface DistributedLock {
    /**
     * tryLock
     *
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return boolean
     * @author dw_wangdonghong
     * @date 2019/4/7 19:29
     */
    boolean tryLock(String lockKey, String requestId, int expireTime);

    /**
     * tryLock
     *
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @param acquireTimeout
     * @return boolean
     * @author dw_wangdonghong
     * @date 2019/4/7 19:29
     */
    boolean tryLock(String lockKey, String requestId, int expireTime, int acquireTimeout);

    /**
     * unLock
     *
     * @param lockKey
     * @param requestId
     * @return boolean
     * @author dw_wangdonghong
     * @date 2019/4/7 19:29
     */
    boolean unLock(String lockKey, String requestId);
}
