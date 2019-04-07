package com.krista.record.redis.sample.distributedlock.redis;

import com.krista.record.redis.sample.distributedlock.DistributedLock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * RedisDistributedLock redis实现的分布式锁
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 19:38
 */
public class RedisDistributedLock implements DistributedLock {
    private static Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 用于可重入锁
     */
    private static ThreadLocal<String> requestIdMap = new ThreadLocal<>();
    private JedisPool jedisPool;

    public RedisDistributedLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public boolean tryLock(String lockKey, String requestId, int expireTime) {
        String existRequestId = requestIdMap.get();
        if (StringUtils.isEmpty(existRequestId)) {
            existRequestId = requestId;
            requestIdMap.set(existRequestId);
        }

        Jedis client = jedisPool.getResource();
        try {
            String result = client.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (result == null) {
                String originRequestId = client.get(lockKey);
                // 同一线程，或者锁已过期
                if (originRequestId.equalsIgnoreCase(requestId) || originRequestId == null) {
                    result = client.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                    return LOCK_SUCCESS.equalsIgnoreCase(result);
                }
            }

            return LOCK_SUCCESS.equalsIgnoreCase(result);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        } finally {
            client.close();
        }
    }

    @Override
    public boolean tryLock(String lockKey, String requestId, int expireTime, int acquireTimeout) {
        long endTime = System.currentTimeMillis() + acquireTimeout * 1000;
        while (System.currentTimeMillis() < endTime) {
            if (tryLock(lockKey, requestId, expireTime)) {
                return true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.error(ex.getMessage(), ex);
            }
        }

        return false;
    }

    @Override
    public boolean unLock(String lockKey, String requestId) {
        String existRequestId = requestIdMap.get();
        if (StringUtils.isEmpty(existRequestId)) {
            return false;
        }

        Jedis client = jedisPool.getResource();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = client.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            if (RELEASE_SUCCESS.equals(result)) {
                requestIdMap.remove();
                return true;
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            client.close();
        }
    }
}
