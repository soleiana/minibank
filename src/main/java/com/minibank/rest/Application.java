package com.minibank.rest;

import com.minibank.config.MVCConfig;
import com.minibank.config.RestConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;


@Import({RestConfig.class, MVCConfig.class})
@EnableAutoConfiguration
public class Application
{
    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Application.class)
                .showBanner(false)
                .run(args);
    }
}
