package com.krista.springframework.v4x.aop.test.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
public class SpringCacheConfig {

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

}
