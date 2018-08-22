package com.love.lylph.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * cors跨域请求
 * Spring 提供了FilterRegistrationBean类，此类提供setOrder方法，可以为filter设置排序值
 * 设置你要允许的网站域名，如果全允许则设为 *
 * 限制 HEADER 或 METHOD
 *
 * Created by 潘淮  on 2018/8/15.<br>
 */
@Configuration
public class CorsConfig{

        private CorsConfiguration buildConfig() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedOrigin("*"); // 1
            corsConfiguration.addAllowedHeader("*"); // 2
            corsConfiguration.addAllowedMethod("POST, GET, OPTIONS, DELETE"); // 3
            return corsConfiguration;
        }

        @Bean
        public FilterRegistrationBean corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", buildConfig()); // 4
            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
            bean.setOrder(0);
            return bean;
        }

    }
