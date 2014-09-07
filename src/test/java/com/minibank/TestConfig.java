package com.minibank;

/**
 * Created by Ann on 07/09/14.
 */
import com.minibank.config.DatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatasourceConfig.class)
@ComponentScan(basePackages = {"com.minibank" })
public class TestConfig
{

}
