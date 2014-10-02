package com.minibank.config;

import com.minibank.rest.Application;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;


/**
 * Created by Ann on 06/09/14.
 */
@Configuration
@ComponentScan(basePackages = {"com.minibank"},
               excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                                                       value = {Application.class})}
               )
@Import({DatasourceConfig.class})
public class RestConfig
{}
