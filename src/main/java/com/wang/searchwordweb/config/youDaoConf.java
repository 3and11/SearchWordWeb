package com.wang.searchwordweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("youdao")
public class youDaoConf {
    private String appKey;
    private String secretKey;
}