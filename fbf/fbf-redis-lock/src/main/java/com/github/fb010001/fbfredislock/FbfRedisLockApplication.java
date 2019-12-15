package com.github.fb010001.fbfredislock;

import com.github.fb010001.fbfredislock.common.CacheKeyGenerator;
import com.github.fb010001.fbfredislock.common.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
// 注解方式配置不自动加载数据库

public class FbfRedisLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(FbfRedisLockApplication.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }
}
