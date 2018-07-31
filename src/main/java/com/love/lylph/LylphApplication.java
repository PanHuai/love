package com.love.lylph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.love.lylph.mapper")
public class LylphApplication {

    public static void main(String[] args) {
        SpringApplication.run(LylphApplication.class, args);
    }
}
