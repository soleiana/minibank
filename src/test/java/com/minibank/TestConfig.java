package com.minibank;

/**
 * Created by Ann on 07/09/14.
 */
import com.minibank.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfig.class})
public class TestConfig
{}
