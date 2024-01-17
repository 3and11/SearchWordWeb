package com.wang.searchwordweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.wang.searchwordweb.mapper")
@SpringBootApplication
public class SearchWordWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchWordWebApplication.class, args);
    }

}
