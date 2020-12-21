package com.eekj.health.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.eekj.health.im.mapper")
public class IMServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IMServiceApplication.class,args);
    }
}
