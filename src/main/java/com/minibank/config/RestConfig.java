package com.minibank.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minibank.rest.Application;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(basePackages = {"com.minibank"},
               excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {Application.class})})
@Import({DatasourceConfig.class})
public class RestConfig {

    @Bean
    public Module javaTimeModule() {
        Module module = new JavaTimeModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return module;
    }

}
