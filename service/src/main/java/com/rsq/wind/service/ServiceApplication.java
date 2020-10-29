package com.rsq.wind.service;

import com.rsq.wind.config.MasterDataSource;
import com.rsq.wind.config.SlaveDataSource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import({MasterDataSource.class, SlaveDataSource.class})
@ComponentScan(value = "com.rsq.wind.cache")
class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
