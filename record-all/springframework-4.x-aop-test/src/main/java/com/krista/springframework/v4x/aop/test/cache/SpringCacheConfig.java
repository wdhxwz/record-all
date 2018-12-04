package com.krista.springframework.v4x.aop.test.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringCacheConfig
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 9:46
 */
@Configuration
@ComponentScan
@EnableCaching(proxyTargetClass = true)
public class SpringCacheConfig extends CachingConfigurerSupport {
    private Logger logger = LoggerFactory.getLogger(SpringCacheConfig.class);

    @Bean(name = "simpleCacheManager")
    public CacheManager simpleCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<Cache> caches = new ArrayList<Cache>();
        ConcurrentMapCache cache1 = new ConcurrentMapCache("mycache");
        ConcurrentMapCache cache2 = new ConcurrentMapCache("mycache2");
        caches.add(cache1);
        caches.add(cache2);

        cacheManager.setCaches(caches);

        return cacheManager;
    }

    @Bean
    @Primary
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

        return redisCacheManager;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisConnectionFactory factory = new JedisConnectionFactory();

        return factory;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                logger.info(e.getMessage());
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                logger.info(e.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                logger.info(e.getMessage());
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                logger.info(e.getMessage());
            }
        };

        return cacheErrorHandler;
    }
}
