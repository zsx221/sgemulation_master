package com.macrochina.net.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用 google guava本地缓存
 */
public class GuavaCacheUtils {

    private static final Logger log = LoggerFactory.getLogger(GuavaCacheUtils.class);

    private static Cache<String,Object> cache;

    static {
        cache = CacheBuilder.newBuilder()
            //设置并发级别为 8,并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置缓存最大容量为100，超过100之后就会按照lru最近虽少使用算法来移除缓存项
            .maximumSize(100)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(24, TimeUnit.HOURS)
            //设置缓存容器的初始容量为10
            .initialCapacity(10)
            .removalListener(new RemovalListener<String, Object>() {
                @Override
                public void onRemoval(RemovalNotification<String, Object> rn) {
                    if(log.isInfoEnabled()){
                        log.info("被移除缓存{}:{}",rn.getKey(),rn.getValue());
                    }
                }
            }).build();
    }

    /**
     * 获取缓存
     */
    public static Object get(String key){
        return StringUtils.isNotEmpty(key) ? cache.getIfPresent(key) : null;
    }

    /**
     * 放入缓存
     */
    public static void put(String key,Object value){
        if(StringUtils.isNotEmpty(key) && value != null){
            cache.put(key,value);
        }
    }

    /**
     * 移除缓存
     */
    public static void remove(String key){
        if(StringUtils.isNotEmpty(key)){
            cache.invalidate(key);
        }
    }

    /**
     * 批量删除缓存
     */
    public static void remove(List<String> keys){
        if(keys != null && keys.size() > 0){
            cache.invalidateAll(keys);
        }
    }
}