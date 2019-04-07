package com.krista.record.redis.sample.limit.redis;

import com.krista.record.redis.sample.limit.RateLimit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * RedisRateLimitWithZset
 * 基于Redis zset进行限流，主要思想为：</br>
 * 将每次访问的时间戳作为zset的score，然后去掉时间窗口内的元素，最后判断时间窗口内的元素个数
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 14:44
 */
public class RedisRateLimitWithZset implements RateLimit {
    private Jedis jedis;

    public RedisRateLimitWithZset(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public boolean isActionAllowed(String field, String actionKey, int period, int maxCount) {
        String redisKey = String.format("hist:%s:%s", field, actionKey);

        // 先判断是否超过最大现在频率
        long existCount = jedis.zcard(redisKey);
        if (existCount >= maxCount) {
            return false;
        }

        long nowTs = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();

        // 将当前访问时间存入zset中
        pipeline.zadd(redisKey, nowTs, "" + nowTs);

        // 去掉时间窗口外的元素
        pipeline.zremrangeByScore(redisKey, 0, nowTs - period * 1000);

        // 统计zset现在的元素个数
        Response<Long> count = pipeline.zcard(redisKey);

        // 设置key的超时时间，以便回收内存
        pipeline.expire(redisKey, period + 1);

        pipeline.exec();
        safeClose(pipeline);

        return count.get() <= maxCount;
    }

    private void safeClose(Pipeline pipeline) {
        try {
            pipeline.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
