package com.wang.searchwordweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class redisConf {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> RedisTemplate = new RedisTemplate<>();
        RedisTemplate.setConnectionFactory(connectionFactory);
        RedisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return RedisTemplate;
    }

}
