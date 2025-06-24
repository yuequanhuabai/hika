package com.e.hika;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
class HikaApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(HikaApplicationTests.class);

    public static String username = "root";
    public static String password = "root";

    @Test
    void contextLoads() {

        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        logger.info("username:{},password:{}", username, password);
        logger.info("credentials:{}", credentials);
        logger.info("encodedCredentials:{}", encodedCredentials);
        System.out.println("======================================================================");
        System.out.println("username:password" + username + ":" + password);
        System.out.println("credentials:" + credentials);
        System.out.println("encodedCredentials:" + encodedCredentials);


    }

}
