package com.minibank.hello;

import com.minibank.config.DatasourceConfig;
import com.minibank.core.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({DatasourceConfig.class})
@EnableAutoConfiguration
public class Application {


    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
