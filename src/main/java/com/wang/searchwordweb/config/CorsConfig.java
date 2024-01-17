package com.wang.searchwordweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("/*") // 允许跨域访问的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许跨域访问的方法
                .allowCredentials(true) // 是否允许发送cookie
                .maxAge(3600); // 预检请求的有效期（秒）
    }
}
