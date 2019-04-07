package com.krista.record.redis.sample;

import com.krista.record.redis.sample.distributedlock.DistributedLock;
import com.krista.record.redis.sample.distributedlock.redis.RedisDistributedLock;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * SecKillService 秒杀服务
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 20:12
 */
public class SecKillService {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
    }

    DistributedLock lock;

    public SecKillService() {
        this.lock = new RedisDistributedLock(pool);
    }

    private static int n = 20;

    public void seckill() {
        if (n <= 0) {
            return;
        }
        String requestId = UUID.randomUUID().toString();
        boolean indentifier = lock.tryLock("resource", requestId, 50000, 10000);
        if (indentifier) {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } else {
            System.out.println(Thread.currentThread().getName() + "没有获得锁");
        }
        System.out.println(--n);

        lock.unLock("resource", requestId);
    }
}
