package com.wang.searchwordweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("baiduconf")
public class BaiDuConf {
    private String appId;
    private String secretKey;
    private String apiKey;
}
