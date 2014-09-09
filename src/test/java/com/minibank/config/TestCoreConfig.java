package com.minibank.config;

/**
 * Created by Ann on 09/09/14.
 */
import com.minibank.config.TestDatasourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = {"com.minibank.core"})
@Import({TestDatasourceConfig.class})
public class TestCoreConfig
{
}
