package com.e.hika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.e.hika.mapper")
@SpringBootApplication
public class HikaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikaApplication.class, args);
    }

}
