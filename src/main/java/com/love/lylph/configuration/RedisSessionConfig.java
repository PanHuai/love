package com.love.lylph.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 86400 = 60*60*24 一天
 * @author PanHuai
 * @data 2018/8/6 16:24
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*1)
public class RedisSessionConfig {
}
