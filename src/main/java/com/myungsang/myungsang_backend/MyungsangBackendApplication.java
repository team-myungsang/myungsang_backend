package com.myungsang.myungsang_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Objects;

@SpringBootApplication
public class MyungsangBackendApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:aws.properties";

    public static final String ELASTIC_BEANSTALK = "spring.config.location="
            + "classpath:application.properties,";

    public static void main(String[] args) {
        if (Objects.equals(System.getProperty("APP_ENV"), "production")) {
            new SpringApplicationBuilder(MyungsangBackendApplication.class).properties(ELASTIC_BEANSTALK).run(args);
        } else {
            new SpringApplicationBuilder(MyungsangBackendApplication.class).properties(APPLICATION_LOCATIONS).run(args);
        }

    }

}
