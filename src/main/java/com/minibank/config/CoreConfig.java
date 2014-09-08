package com.minibank.config;

import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.BankParamsRepositoryImpl;
import com.minibank.core.services.LoanEventHandler;
import com.minibank.core.services.LoanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by Ann on 06/09/14.
 */
@ComponentScan(basePackages = {"com.minibank.core"})
@Import({DatasourceConfig.class})
public class CoreConfig
{}
