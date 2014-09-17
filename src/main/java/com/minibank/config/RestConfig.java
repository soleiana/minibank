package com.minibank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


/**
 * Created by Ann on 06/09/14.
 */
@ComponentScan(basePackages = {"com.minibank"})
@Import({DatasourceConfig.class})
public class RestConfig
{}
