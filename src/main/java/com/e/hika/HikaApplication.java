package com.e.hika;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@MapperScan("com.e.hika.mapper")
@SpringBootApplication
public class HikaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikaApplication.class, args);
    }

}
