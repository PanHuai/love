package com.love.lylph.common;

import com.love.lylph.response.SessionInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author PanHuai
 * @data 2018/8/6 10:38
 */
public class RedisUtils {

    private final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final RedisUtils redisUtils = new RedisUtils();

    public static RedisUtils getRedisUtils() {
        return redisUtils;
    }

    /**
     * 更新 添加 缓存
     * @param sessionInfo
     */
    public void put(SessionInfo sessionInfo) {

        try {
            redisTemplate.opsForValue().set(sessionInfo.getKey(),sessionInfo.getValue());
            redisTemplate.expire(sessionInfo.getKey(),sessionInfo.getExpiryTime() == null? 60l: sessionInfo.getExpiryTime(), TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("更新或添加缓存失败...",e);
        }
    }

    /**
     * 得到缓存信息
     */
    public Object getValue(String key) {

        try {
            if (StringUtils.isBlank(key)) {
                return null;
            }
            Object o = redisTemplate.opsForValue().get(key);
            return o;
        } catch (Exception e) {
            logger.error("key:"+key+" 得到缓存信息失败...",e);
        }
        return null;
    }


    /**
     * 延长缓存有效期
     */
    public void expiryTime(String key,Long expiryTime) {

        try {
            if (StringUtils.isBlank(key) || expiryTime == null) {
                return;
            }
            redisTemplate.expire(key, expiryTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("key:"+key+" 延长缓存有效期失败...",e);
        }
    }

    /**
     * 删除缓存
     */
    public void delete(String key) {

        try {
            if (StringUtils.isBlank(key)) {
                return;
            }
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("key:"+key+" 删除缓存失败...",e);
        }
    }

    /**
     * 删除多个缓存
     */
    public void delete(Set<String> key) {

        try {
            if (key.isEmpty()) {
                return;
            }
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("多个key删除缓存失败...",e);
        }
    }

    /**
     * 得到以某个字段为开头的缓存key
     */
    public Set<String> getKeys(String keyHeader) {

        try {
            if (StringUtils.isBlank(keyHeader)) {
                return null;
            }
            Set<String> keys = redisTemplate.keys(keyHeader);
            return keys;
        } catch (Exception e) {
            logger.error("得到以"+keyHeader+"字段为开头的缓存失败...",e);
        }
        return null;
    }


}
