package com.krista.record.redis.sample;

import com.google.common.util.concurrent.RateLimiter;
import com.krista.record.redis.sample.distributedlock.DistributedLock;
import com.krista.record.redis.sample.distributedlock.redis.RedisDistributedLock;
import com.krista.record.redis.sample.limit.RateLimit;
import com.krista.record.redis.sample.limit.redis.RedisRateLimitWithList;
import com.krista.record.redis.sample.limit.redis.RedisRateLimitWithZset;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * RedisSampleApplication
 * <p>
 * host: redis.krista.com
 * database: 1
 * port: 6379
 * password: 1234567890poiuytrewqasdfghjklmnbvcxz
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 14:32
 */
public class RedisSampleApplication {
    public static void main(String[] args) {
        SecKillService secKillService = new SecKillService();

        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(() -> {
                secKillService.seckill();
            });
            thread.start();
        }
    }

    private static void rateLimitTest() {
        Jedis jedis = new Jedis();
        int period = 60;
        int maxCount = 5;
        int maxIndex = 10;

        RateLimit rateLimit = new RedisRateLimitWithZset(jedis);
        for (int index = 0; index < maxIndex; index++) {
            System.out.println(index + ":" + rateLimit.isActionAllowed("123", "login", period, maxCount));
        }

        rateLimit = new RedisRateLimitWithList(jedis);
        for (int index = 0; index < maxIndex; index++) {
            System.out.println(index + ":" + rateLimit.isActionAllowed("123", "smsSend", period, maxCount));
        }
    }
}
