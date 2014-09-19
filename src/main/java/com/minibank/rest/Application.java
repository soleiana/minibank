package com.minibank.rest;

import com.minibank.config.DatasourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.minibank")
@Import({DatasourceConfig.class})
@EnableAutoConfiguration
public class Application {


    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
