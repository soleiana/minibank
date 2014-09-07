package com.minibank.config;

/**
 * Created by Ann on 06/09/14.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.minibank.rest.controller"})
public class MVCConfig {
}
