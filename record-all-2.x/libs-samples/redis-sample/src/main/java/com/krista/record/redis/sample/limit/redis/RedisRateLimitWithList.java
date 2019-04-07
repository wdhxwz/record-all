package com.krista.record.redis.sample.limit.redis;

import com.krista.record.redis.sample.limit.RateLimit;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * RedisRateLimitWithList
 * 基于Redis的list进行访问频率访问：
 * 基本原理：将需要限定的对象的唯一标识作为key，
 * 如userId、Ip,键对应的值为该对象每次的访问时间列表，
 * 如列表的元素个数超出了限定频率，则判断最早的时间和当前的时间差是不是超过指定的时间段，
 * 若不是，则将最早的时间去掉，把当前时间加进去，否则，访问频率超过了限定
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 15:31
 */
public class RedisRateLimitWithList implements RateLimit {
    private Jedis jedis;

    public RedisRateLimitWithList(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public boolean isActionAllowed(String field, String actionKey, int period, int maxCount) {
        String redisKey = String.format("hist:%s:%s", field, actionKey);
        // 先判断是否超过最大现在频率
        long existCount = jedis.llen(redisKey);
        long nowTs = System.currentTimeMillis();
        if (existCount >= maxCount) {
            // 从左边弹出就得从右边push进去
            List<String> list = jedis.lrange(redisKey, 0, 0);

            // 判断最早的元素，是否在时间窗口之外
            if ((nowTs - period * 1000) > Long.parseLong(list.get(0))) {
                jedis.lpop(redisKey);
                jedis.rpush(redisKey, "" + nowTs);

                return true;
            }

            return false;
        }

        jedis.rpush(redisKey, "" + nowTs);

        // 设置key的超时时间，以便回收内存
        jedis.expire(redisKey, period + 1);

        return true;
    }
}
